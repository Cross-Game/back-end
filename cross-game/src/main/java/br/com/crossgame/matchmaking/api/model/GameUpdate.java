package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;

public record GameUpdate(Long id, String gameName, GameGenre gameGenre) {
}
