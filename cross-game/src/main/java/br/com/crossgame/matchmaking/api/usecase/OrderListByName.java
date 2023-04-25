package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Friends;
import br.com.crossgame.matchmaking.internal.entity.User;

import java.util.List;

public interface OrderListByName {
    List<Friends> execute(List<Friends> friends);
    List<User> executeUser(List<User> users);
}
