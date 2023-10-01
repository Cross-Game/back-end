package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.enums.NotificationState;

public interface ResponseNotify {

    void execute(NotificationState response, Long userId, Long notificationId, Long roomId);
}
