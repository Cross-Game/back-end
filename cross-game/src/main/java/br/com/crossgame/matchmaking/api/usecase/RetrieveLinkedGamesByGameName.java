package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;

import java.util.List;
import java.util.Optional;

public interface RetrieveLinkedGamesByGameName {
    Optional<List<GenericGame>> execute(Long id);
}
