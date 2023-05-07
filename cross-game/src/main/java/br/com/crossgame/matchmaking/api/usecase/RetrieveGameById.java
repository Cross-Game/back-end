package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Game;

public interface RetrieveGameById {

    Game execute(Long gameId);
}
