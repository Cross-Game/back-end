package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.GameFunction;
import br.com.crossgame.matchmaking.internal.entity.enums.SkillLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record UserGameCreate(Long id,
                             boolean isFavoriteGame,
                             @NotBlank
                             String userNickname,
                             @NotBlank
                             String gamerId,
                             @NotNull
                             SkillLevel skillLevel,
                             GameFunction gameFunction,
                             List<Long> GenericGamersIds) {
}
