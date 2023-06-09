package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserGameController;
import br.com.crossgame.matchmaking.api.model.UserGameCreate;
import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.model.UsernameResponse;
import br.com.crossgame.matchmaking.api.usecase.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.ResponseEntity;
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
    private ValidateUsername validateUsername;

    @Override
    public UserGameResponse linkGameToUser(UserGameCreate userGameCreate, Long gameId, Long userId) {
        return this.linkGameToUser.execute(userGameCreate, gameId, userId);
    }

    @Override
    public List<UserGameResponse> retrieveLinkedGamesByUserId(Long userId) {
        return this.retrieveLinkedGamesByUserId.execute(userId);
    }

    @Override
    public UserGameResponse updateLinkedGameToUser(UserGameCreate userGame) {
        return this.updateLinkedGameToUser.execute(userGame);
    }

    @Override
    public void deleteLinkedGameToUser(Long userId, Long userGameId) {
        this.deleteLinkedGameToUser.execute(userId, userGameId);
    }

    @Override
    public ResponseEntity<UsernameResponse> validateUsername(Long userId, String username) {
        return validateUsername.execute(userId,username);
    }
}
