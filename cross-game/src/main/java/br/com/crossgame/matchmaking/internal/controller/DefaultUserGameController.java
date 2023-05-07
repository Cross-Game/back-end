package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserGameController;
import br.com.crossgame.matchmaking.api.usecase.DeleteLinkedGameToUser;
import br.com.crossgame.matchmaking.api.usecase.LinkGameToUser;
import br.com.crossgame.matchmaking.api.usecase.RetrieveLinkedGamesByUserId;
import br.com.crossgame.matchmaking.api.usecase.UpdateLinkedGameToUser;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(UserGameController.class)
public class DefaultUserGameController implements UserGameController{

    private LinkGameToUser linkGameToUser;
    private RetrieveLinkedGamesByUserId retrieveLinkedGamesByUserId;
    private UpdateLinkedGameToUser updateLinkedGameToUser;
    private DeleteLinkedGameToUser deleteLinkedGameToUser;

    @Override
    public UserGame linkGameToUser(UserGame userGame, Long gameId, Long userId) {
        return this.linkGameToUser.execute(userGame, gameId, userId);
    }

    @Override
    public List<UserGame> retrieveLinkedGamesByUserId(Long userId) {
        return this.retrieveLinkedGamesByUserId.execute(userId);
    }

    @Override
    public UserGame updateLinkedGameToUser(UserGame userGame) {
        return this.updateLinkedGameToUser.execute(userGame);
    }

    @Override
    public void deleteLinkedGameToUser(Long userId, Long userGameId) {
        this.deleteLinkedGameToUser.execute(userId, userGameId);
    }
}
