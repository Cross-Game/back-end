package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.RoomData;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;

import java.util.List;

public interface RetrieveRooms {
    public List<RoomData> execute();
}
