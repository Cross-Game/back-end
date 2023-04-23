package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;

public record UserAndFriend(Long id, String username, String email, Role role, Boolean isOnline, Friend friend) {
}
