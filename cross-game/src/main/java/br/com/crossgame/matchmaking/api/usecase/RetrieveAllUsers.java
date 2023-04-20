package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.internal.entity.User;

import java.util.List;

public interface RetrieveAllUsers {

    List<UserData> execute();
}
