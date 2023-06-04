package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserPlatformController;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;
import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(UserPlatformController.class)
public class DefaultUserPlatformController implements UserPlatformController{

    private RetrieveLinkedGamePlatformsByUserId retrievePlatformsToUser;
    private UpdateGamePlatformsForUserById updateGamePlatformsForUserById;

    @Override
    public List<GameplayPlatformType> retrieveLinkedGamePlatformsByUserId(Long userId) {
        return this.retrievePlatformsToUser.execute(userId);
    }

    @Override
    public List<GameplayPlatformType> updateGamePlatformsForUserById(Long userId, List<GameplayPlatformType> platforms) {
        return this.updateGamePlatformsForUserById.execute(userId, platforms);
    }
}
