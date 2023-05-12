package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameResponse;

import java.util.List;

public interface RetrieveLinkedGamesByUserId {
    List<UserGameResponse> execute(Long userId);
}
