package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndFriend;

public interface ConfirmFriendRequest {
    UserAndFriend execute(Long userId, String friendUsername);
}
