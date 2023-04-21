package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Friends;

import java.util.List;

public interface OrderListByName {
    List<Friends> execute(List<Friends> friends);
}
