package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.GameModeAndRole;
import br.com.crossgame.matchmaking.internal.entity.Plataform;
import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;

import java.util.List;

public record GameData(Long id,
                       String gameName,
                       GameGenre gameGenre,
                       List<GameModeAndRole> gameModeAndRoles,
                       List<Plataform> plataforms) {
}
