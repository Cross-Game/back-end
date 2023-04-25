package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Friend;

import java.util.List;

public interface RetrieveAllFriendsByUserId {

    List<Friend> execute(Long userId);
}
