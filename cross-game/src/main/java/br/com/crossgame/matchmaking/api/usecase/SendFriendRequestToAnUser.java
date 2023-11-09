package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndFriend;
import br.com.crossgame.matchmaking.internal.entity.Friend;

public interface SendFriendRequestToAnUser {

    UserAndFriend execute(Long userId, Friend friend);
}
