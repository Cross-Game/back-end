package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;

import java.util.List;

public record GameResponse(Long id,
                           String gameName,
                           GameGenre gameGenre,
                           List<PlataformResponse> plataforms) {
}
