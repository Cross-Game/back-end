package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;

import java.util.Optional;

public interface RetrieveGameIgdbByName {
    Optional<GenericGame> execute (String gameName);
}
