package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameCreate;
import br.com.crossgame.matchmaking.api.model.UserGameResponse;

public interface UpdateLinkedGameToUser {
    UserGameResponse execute(UserGameCreate userGameCreate);
}
