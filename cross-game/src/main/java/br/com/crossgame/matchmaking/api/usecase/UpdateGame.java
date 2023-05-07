package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.GameUpdate;
import br.com.crossgame.matchmaking.internal.entity.Game;

public interface UpdateGame {
    Game execute(GameUpdate gameUpdate);
}
