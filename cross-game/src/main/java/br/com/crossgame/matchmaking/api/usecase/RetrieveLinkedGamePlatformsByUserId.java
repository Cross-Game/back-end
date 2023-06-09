package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;

import java.util.List;

public interface RetrieveLinkedGamePlatformsByUserId {
    List<GameplayPlatformType> execute(Long userId);
}
