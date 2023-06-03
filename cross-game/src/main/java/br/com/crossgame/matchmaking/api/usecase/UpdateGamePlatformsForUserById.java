package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;
import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;

import java.util.List;

public interface UpdateGamePlatformsForUserById {
    List<GameplayPlatformType> execute(Long userId, List<GameplayPlatformType> gameplayPlatformTypes);
}
