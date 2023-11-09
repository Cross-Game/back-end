package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.GameResponse;
import br.com.crossgame.matchmaking.api.model.GameUpdate;

public interface UpdateGame {
    GameResponse execute(GameUpdate gameUpdate);
}
