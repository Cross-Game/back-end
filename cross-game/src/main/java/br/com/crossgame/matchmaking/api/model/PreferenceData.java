package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.Preferences;

public record PreferenceData(Long id, Preferences preferences) {
}
