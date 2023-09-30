package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.GamesIgdbControler;
import br.com.crossgame.matchmaking.api.controller.GamesControler;
//import br.com.crossgame.matchmaking.api.usecase.CreateGameApi;
import br.com.crossgame.matchmaking.api.usecase.CreateGameApi;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllGamesIgdb;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameByName;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.TypeImage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class DefaultGamesIgdbController implements GamesIgdbControler {
    private final RetrieveGameByName retrieveGameByName;
    private final CreateGameApi createGameApi;
    private final RetrieveAllGamesIgdb retrieveAllGamesIgdb;


    @Override
    public GenericGame retrieveGameApi(String gameName, TypeImage typeImage) throws IOException {
        return retrieveGameByName.execute(gameName, typeImage);
    public List<GenericGame> retrieveAllGames() {
        return retrieveAllGamesIgdb.execute();
    }

    @Override
    public Optional<GenericGame> retrieveGameIgdbByName(String gameName) throws IOException {
        return Optional.empty();
    }

    @Override
    public Optional<GenericGame> createGame(String gameName) throws IOException {
        return createGameApi.execute(gameName);
    }
}
