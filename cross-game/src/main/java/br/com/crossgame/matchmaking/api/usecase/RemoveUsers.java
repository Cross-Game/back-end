package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;

import java.util.List;

public interface RemoveUsers {
    void execute(List<User> users, Long adminId);
}
