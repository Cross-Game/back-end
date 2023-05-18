package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.GameData;
import br.com.crossgame.matchmaking.api.model.GameResponse;

public interface CreateGame {

    GameResponse execute(GameData game);
}
