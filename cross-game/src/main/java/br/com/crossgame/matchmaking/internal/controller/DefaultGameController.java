package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.GameController;
import br.com.crossgame.matchmaking.api.model.GameUpdate;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.Game;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(GameController.class)
public class DefaultGameController implements GameController{

    private CreateGame createGame;
    private RetrieveAllGames retrieveAllGames;
    private RetrieveGameById retrieveGameById;
    private DeleteGameById deleteGameById;
    private UpdateGame updateGame;

    @Override
    public Game createGame(Game game) {
        return this.createGame.execute(game);
    }

    @Override
    public List<Game> retrieveAllGames() {
        return this.retrieveAllGames.execute();
    }

    @Override
    public Game retrieveGameById(Long gameId) {
        return this.retrieveGameById.execute(gameId);
    }

    @Override
    public void deleteGameById(Long gameId) {
        this.deleteGameById.execute(gameId);
    }

    @Override
    public Game updateGame(GameUpdate gameUpdate) {
        return this.updateGame.execute(gameUpdate);
    }
}
