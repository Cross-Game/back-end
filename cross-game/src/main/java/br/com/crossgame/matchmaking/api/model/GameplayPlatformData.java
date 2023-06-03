package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;

public record GameplayPlatformData(Long id, GameplayPlatformType gameplayPlatformType) {
}
