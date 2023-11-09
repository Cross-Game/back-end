package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;

import java.util.List;

public record UserAndPreference(Long id, String username, String email, Role role, Boolean isOnline, List<PreferenceData> preference) {
}
