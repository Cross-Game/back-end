package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;

import java.util.List;

public interface RetrieveLinkedGamePlatformsByUserId {
    List<GameplayPlatform> execute(Long userId);
}
