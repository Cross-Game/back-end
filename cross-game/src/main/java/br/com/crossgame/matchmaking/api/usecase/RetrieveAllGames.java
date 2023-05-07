package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Game;

import java.util.List;

public interface RetrieveAllGames {

    List<Game> execute();
}
