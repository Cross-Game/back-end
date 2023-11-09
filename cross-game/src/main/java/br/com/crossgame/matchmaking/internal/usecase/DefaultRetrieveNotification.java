package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.api.usecase.RetrieveNotification;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.NotificationRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.NotificationBuildUtils;
import br.com.crossgame.matchmaking.internal.utils.PilhaObj;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class DefaultRetrieveNotification implements RetrieveNotification {
    private UserRepository userRepository;

    private NotificationRepository notificationRepository;
    @Override
    public List<NotificationResponse> execute(Long idUser) {
        return this.retrieveNotification(idUser);
    }

    private List<NotificationResponse> retrieveNotification(Long idUser){

        User user = userRepository.findById(idUser).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with id %d not found!",idUser)));

        PilhaObj<Notification> pilhaObj = new PilhaObj<>(user.getNotifies().size());

        user.getNotifies().forEach(pilhaObj::push);

        List<Notification> notifications = new ArrayList<>();
        List<NotificationResponse> notificationResponses = new ArrayList<>();

        while (!pilhaObj.isEmpty()){
            notifications.add(pilhaObj.pop());
        }
        notifications.forEach(n -> notificationResponses.add( NotificationBuildUtils.transform(n)));

        return notificationResponses;
    }
}
