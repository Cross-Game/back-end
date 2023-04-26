package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Friend;

import java.util.List;

public interface OrderListByName {
    public List<Friend> execute(List<Friend> Friend) ;

}
