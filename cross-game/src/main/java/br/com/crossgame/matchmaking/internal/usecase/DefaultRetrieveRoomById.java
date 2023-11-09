package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.RoomData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveRoomById;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DefaultRetrieveRoomById implements RetrieveRoomById {
    private TeamRoomRepository teamRoomRepository;
    @Override
    public RoomData execute(Long id) {
        TeamRoom teamRoom = teamRoomRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Room not found!"));

        return DefaultRetrieveRooms.convert(teamRoom);
    }
}
