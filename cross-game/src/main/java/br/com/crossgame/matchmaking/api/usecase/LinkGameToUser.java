package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.UserGame;

public interface LinkGameToUser {

    UserGame execute(UserGame userGame, Long gameId, Long userId);
}
