package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;

public interface AddFriendToAnUser {

    User execute(Long userId, Friend friend);
}
