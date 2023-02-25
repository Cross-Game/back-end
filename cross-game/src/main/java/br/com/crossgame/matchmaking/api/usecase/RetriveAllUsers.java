package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;

import java.util.List;

public interface RetriveAllUsers {

    public List<User> execute();
}
