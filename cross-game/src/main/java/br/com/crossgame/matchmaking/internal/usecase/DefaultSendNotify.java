package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.api.observer.Observer;
import br.com.crossgame.matchmaking.api.usecase.SendNotify;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;
import br.com.crossgame.matchmaking.internal.repository.NotificationRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.NotificationBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class DefaultSendNotify implements SendNotify {

    private UserRepository userRepository;

    private NotificationRepository notificationRepository;


    private List<Observer> observers = new ArrayList<>();

    private void addObserver(User observer)  {
        observers.add(observer);
    }



    @Override
    public NotificationResponse execute(Long idUser, NotificationType notification, String message, String description, NotificationState state) {
       Notification notification1 = this.createNotification(notification,message,description,idUser,state);
       return NotificationBuildUtils.transform(notification1);
    }
    private Notification createNotification(NotificationType notificationType, String message, String description, Long idUser, NotificationState state) {
        User user = userRepository.findById(idUser).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %d not found!", idUser)));
        this.addObserver(user);

        ZoneId brasiliaTimeZone = ZoneId.of("America/Sao_Paulo");
        LocalDateTime nowInBrasilia = LocalDateTime.now(brasiliaTimeZone);

        Notification notify = new Notification(notificationType, message, description, nowInBrasilia, user, state);
        notify.notifyObservers(notify, observers);
        return notificationRepository.save(notify);
    }

}
