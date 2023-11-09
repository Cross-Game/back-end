package br.com.crossgame.matchmaking.api.observer;

import br.com.crossgame.matchmaking.internal.entity.Notification;

public interface Observer {
    void update(Notification notification);
}
