package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;

import java.util.List;

public interface RetrieveHistoricOfUsers {
    List<UserData> execute(Long idRoom);
}
