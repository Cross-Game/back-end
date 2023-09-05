package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.GamesControler;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameByName;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class DefaultGamesController implements GamesControler {
    private final RetrieveGameByName retrieveGameByName;
    @Override
    public GenericGame retrieveGame(String gameName) throws IOException {
        return retrieveGameByName.execute(gameName);
    }
}
