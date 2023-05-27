package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;

public interface SendNotify {
    NotificationResponse execute(Long idUser, NotificationType notification, String message, String description);

}
