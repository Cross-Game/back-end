package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.Plataform;
import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record GameData(Long id,
                       @NotBlank
                       String gameName,
                       @NotNull
                       GameGenre gameGenre,
                       @NotNull
                       List<Plataform> plataforms) {
}
