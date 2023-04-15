package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Friends;
import br.com.crossgame.matchmaking.internal.entity.User;

public interface AddFriendToAnUser {

    User execute(Long userId, Friends friend);
}
