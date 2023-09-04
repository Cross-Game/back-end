package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;

public interface DeleteNotification {
    NotificationResponse execute(Long id);
}
