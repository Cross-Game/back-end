package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;

import java.io.IOException;

public interface RetrieveGameByName {
    GenericGame execute(String gameName) throws IOException;
}
