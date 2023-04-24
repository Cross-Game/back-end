package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.SendNotify;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.Room;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;
import br.com.crossgame.matchmaking.internal.repository.NotificationRepository;
import br.com.crossgame.matchmaking.internal.repository.RoomRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DefaultSendNotify implements SendNotify {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private RoomRepository roomRepository;

    private List<User> observers = new ArrayList<>();

    private void addObserver(User observer) {
        observers.add(observer);
    }

    private void createNotification(NotificationType notificationType, String message, String description, LocalDateTime date) {
        Notification notify = new Notification(notificationType, message, description, date);
        notify.notifyObservers(observers);
    }
    @Override
    public void execute(Long idUser, Long idRoom, Notification notification) {
        Room room = this.roomRepository.findById(idRoom)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Sala com o id %s não foi encontrada!",idRoom)));

       User user = this.userRepository.findById(idUser)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Usuário com o id %s não foi encontrado!",idUser)));
       user.update(notification);

       List<User> users = room.getUsers();
       notification.notifyObservers(users);
    }

}
