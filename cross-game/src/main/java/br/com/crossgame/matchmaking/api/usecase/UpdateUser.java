package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserCreate;
import br.com.crossgame.matchmaking.internal.entity.User;

public interface UpdateUser {
    User execute(UserCreate userCreate);
}
