package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.GameResponse;

import java.util.List;

public interface RetrieveAllGames {

    List<GameResponse> execute();
}
