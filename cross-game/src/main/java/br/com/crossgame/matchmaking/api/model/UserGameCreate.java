package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.SkillLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserGameCreate(Long id,
                            @NotNull
                             boolean isFavoriteGame,
                             @NotBlank
                             String userNickname,
                             @NotBlank
                             String gamerId,
                             @NotNull
                             SkillLevel skillLevel) {
}
