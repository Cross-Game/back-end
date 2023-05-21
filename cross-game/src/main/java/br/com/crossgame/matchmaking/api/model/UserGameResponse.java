package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.GameFunction;
import br.com.crossgame.matchmaking.internal.entity.enums.SkillLevel;

public record UserGameResponse(Long id,
                               boolean isFavoriteGame,
                               String userNickname,
                               String gamerId,
                               SkillLevel skillLevel,
                               GameFunction gameFunction,
                               Long gameId,
                               Long userId) {
}
