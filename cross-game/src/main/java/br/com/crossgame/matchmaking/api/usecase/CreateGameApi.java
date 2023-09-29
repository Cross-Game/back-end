package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;

import java.io.IOException;
import java.util.Optional;

public interface CreateGameApi {
    Optional<GenericGame>  execute(String name) throws IOException;
}
