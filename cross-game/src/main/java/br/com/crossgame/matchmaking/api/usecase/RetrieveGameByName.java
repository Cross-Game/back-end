package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.TypeImage;

import java.io.IOException;

public interface RetrieveGameByName {
    GenericGame execute(String gameName, TypeImage typeImage) throws IOException;
}
