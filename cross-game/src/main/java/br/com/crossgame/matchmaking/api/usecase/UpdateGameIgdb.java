package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;

public interface UpdateGameIgdb {
    GenericGame execute(GenericGame game,Long id);
}
