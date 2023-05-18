package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.GameController;
import br.com.crossgame.matchmaking.api.model.GameData;
import br.com.crossgame.matchmaking.api.model.GameResponse;
import br.com.crossgame.matchmaking.api.model.GameUpdate;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.utils.GameResponseBuildUtils;
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
    public GameResponse createGame(GameData gameData) {
        return this.createGame.execute(gameData);
    }

    @Override
    public List<GameResponse> retrieveAllGames() {
        return this.retrieveAllGames.execute();
    }

    @Override
    public GameResponse retrieveGameById(Long gameId) {
        return GameResponseBuildUtils.transform(this.retrieveGameById.execute(gameId));
    }

    @Override
    public void deleteGameById(Long gameId) {
        this.deleteGameById.execute(gameId);
    }

    @Override
    public GameResponse updateGame(GameUpdate gameUpdate) {
        return this.updateGame.execute(gameUpdate);
    }
}
