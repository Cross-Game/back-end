package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserGameController;
import br.com.crossgame.matchmaking.api.model.UserGameCreate;
import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.model.UsernameResponse;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(UserGameController.class)
public class DefaultUserGameController implements UserGameController{

    private LinkGameToUser linkGameToUser;
    private RetrieveLinkedGamesByUserId retrieveLinkedGamesByUserId;
    private UpdateLinkedGameToUser updateLinkedGameToUser;
    private DeleteLinkedGameToUser deleteLinkedGameToUser;
    private ValidateUsername validateUsername;
    private RetrieveLinkedGamesByGameName retrieveLinkedGamesByGameName;

    @Override
    public UserGameResponse linkGameToUser(UserGameCreate userGameCreate, Long gameId, Long userId) {
        return this.linkGameToUser.execute(userGameCreate, gameId, userId);
    }

    @Override
    public List<UserGameResponse> retrieveLinkedGamesByUserId(Long userId) {
        return this.retrieveLinkedGamesByUserId.execute(userId);
    }

    @Override
    public Optional<List<GenericGame>> retrieveLinkedGamesByGameName(Long userId, String gameName) {
        return retrieveLinkedGamesByGameName.execute(userId,gameName);
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
    public ResponseEntity<UsernameResponse> validateUsername(Long userId, String username,String gameName) {
        return validateUsername.execute(userId,username,gameName);
    }
}
