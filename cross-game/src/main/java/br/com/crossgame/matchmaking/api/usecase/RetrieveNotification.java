package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.internal.entity.Notification;

import java.util.List;

public interface RetrieveNotification {
    public List<NotificationResponse> execute(Long idUser);
}
