package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.NotifyController;
import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;
import br.com.crossgame.matchmaking.internal.usecase.DefaultDeleteNotification;
import br.com.crossgame.matchmaking.internal.usecase.DefaultRetrieveNotification;
import br.com.crossgame.matchmaking.internal.usecase.DefaultSendNotify;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DefaultNotifyController implements NotifyController {

    private DefaultSendNotify sendNotify;

    private DefaultRetrieveNotification retrieveNotification;

    private DefaultDeleteNotification deleteNotification;

    @Override
    public NotificationResponse sendNotify(Long idUser, NotificationType notification, String message, String description, NotificationState state) {
        return sendNotify.execute(idUser, notification, message, description, state);
    }

    @Override
    public List<NotificationResponse> retrieveNotify(Long idUser) {
        return retrieveNotification.execute(idUser);
    }

    @Override
    public NotificationResponse negateNotify(Long id) {
        return deleteNotification.execute(id);
    }
}
