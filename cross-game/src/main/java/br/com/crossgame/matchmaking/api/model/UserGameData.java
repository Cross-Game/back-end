package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.SkillLevel;

public record UserGameData(Long id,
                           boolean isFavoriteGame,
                           String userNickname,
                           String gamerId,
                           SkillLevel skillLevel,
                           GameData gamesData) {
}
