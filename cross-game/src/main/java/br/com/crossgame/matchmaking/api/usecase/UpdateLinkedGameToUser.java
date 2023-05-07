package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.UserGame;

public interface UpdateLinkedGameToUser {
    UserGame execute(UserGame userGame);
}
