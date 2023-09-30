package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;

import java.util.List;

public interface RetrieveAllGamesIgdb {
    List<GenericGame> execute();
}
