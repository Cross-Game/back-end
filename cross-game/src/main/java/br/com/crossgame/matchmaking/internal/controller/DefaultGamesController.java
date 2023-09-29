package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.GamesControler;
//import br.com.crossgame.matchmaking.api.usecase.CreateGameApi;
import br.com.crossgame.matchmaking.api.usecase.CreateGameApi;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameByName;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.TypeImage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class DefaultGamesController implements GamesControler {
    private final RetrieveGameByName retrieveGameByName;
    private final CreateGameApi createGameApi;
    @Override
    public GenericGame retrieveGameApi(String gameName, TypeImage typeImage) throws IOException {
        return retrieveGameByName.execute(gameName, typeImage);
    }

    @Override
    public Optional<GenericGame> createGame(String gameName) throws IOException {
        return createGameApi.execute(gameName);
    }
}
