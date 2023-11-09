package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.PlataformType;

public record PlataformResponse(Long id, PlataformType plataformType) {
}
