package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.RoomData;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import org.springframework.stereotype.Service;

@Service
public interface CreateRoom {
    public RoomData execute(TeamRoom teamRoom, Long userId);
}
