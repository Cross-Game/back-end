package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import org.springframework.stereotype.Service;

@Service
public interface CreateRoom {

    public TeamRoom execute(TeamRoom teamRoom);
}
