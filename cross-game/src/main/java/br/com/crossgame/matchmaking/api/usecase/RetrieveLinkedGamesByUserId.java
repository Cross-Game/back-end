package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.UserGame;

import java.util.List;

public interface RetrieveLinkedGamesByUserId {
    List<UserGame> execute(Long userId);
}
