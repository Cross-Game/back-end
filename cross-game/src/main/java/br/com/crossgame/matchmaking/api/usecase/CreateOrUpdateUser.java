package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;

public interface CreateOrUpdateUser {
    public User execute(User user);
}
