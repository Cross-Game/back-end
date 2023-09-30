package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.enums.GameFunction;
import br.com.crossgame.matchmaking.internal.entity.enums.SkillLevel;

import java.util.List;

public record UserGameResponse(Long id,
                               boolean isFavoriteGame,
                               String userNickname,
                               String gamerId,
                               SkillLevel skillLevel,
                               GameFunction gameFunction,
                               Long userId,
                               List<GenericGame> genericGames) {
}
