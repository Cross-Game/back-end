package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.RoomData;

public interface RetrieveRoomById {
    RoomData execute(Long id);
}
