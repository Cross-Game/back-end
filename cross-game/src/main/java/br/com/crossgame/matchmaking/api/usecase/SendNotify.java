package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;

public interface SendNotify {
    NotificationResponse execute(Long idUser, NotificationType notification, String message, String description, NotificationState state);

}
