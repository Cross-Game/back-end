package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;

public record UserAndFeedback(Long id, String username, String email, Role role, Boolean isOnline, Feedback feedback) {
}
