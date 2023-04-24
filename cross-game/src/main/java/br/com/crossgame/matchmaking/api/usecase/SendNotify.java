package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Notification;

public interface SendNotify {
    void execute(Long idUser, Long idRoom, Notification notification);
}
