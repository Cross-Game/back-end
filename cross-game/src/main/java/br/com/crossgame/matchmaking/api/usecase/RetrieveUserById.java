package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;

public interface RetrieveUserById {
    public User execute(Long id);
}
