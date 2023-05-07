package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Game;

public interface CreateGame {

    Game execute(Game game);
}
