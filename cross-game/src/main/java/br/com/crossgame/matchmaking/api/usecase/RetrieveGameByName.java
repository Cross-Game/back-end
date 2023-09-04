package br.com.crossgame.matchmaking.api.usecase;

import java.io.IOException;

public interface RetrieveGameByName {
    String execute(String gameName) throws IOException;
}
