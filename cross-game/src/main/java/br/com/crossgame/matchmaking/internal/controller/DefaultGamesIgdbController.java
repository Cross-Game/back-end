package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.GamesIgdbControler;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.enums.TypeImage;
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
    private final RetrieveGameIgdbByName retrieveGameIgdbByName;
    private final UpdateGameIgdb updateGameIgdb;
    private final DeleteGameIgdb deleteGameIgdb;


    @Override
    public GenericGame retrieveGameApi(String gameName, TypeImage typeImage) throws IOException {
        return retrieveGameByName.execute(gameName, typeImage);
    }


    public List<GenericGame> retrieveAllGames() {
        return retrieveAllGamesIgdb.execute();
    }

    @Override
    public GenericGame retrieveGameIgdbByName(String gameName) throws IOException {
        return retrieveGameIgdbByName.execute(gameName);
    }

    @Override
    public Optional<GenericGame> createGame(String gameName) throws IOException {
        return createGameApi.execute(gameName);
    }

    @Override
    public GenericGame updateGameIgdb(GenericGame game, Long id) {
        return updateGameIgdb.execute(game,id);
    }

    @Override
    public void deleteGame(Long id) {
    this.deleteGameIgdb.execute(id);
    }


}
