package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.TeamRoomController;
import br.com.crossgame.matchmaking.api.controller.UserController;
import br.com.crossgame.matchmaking.api.usecase.CreateRoom;
import br.com.crossgame.matchmaking.api.usecase.RetrieveRooms;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(TeamRoomController.class)
public class DefaultTeamRoomController implements TeamRoomController {
    private CreateRoom createRoom;
    private RetrieveRooms retrieveRooms;
    @Override
    public TeamRoom createRoom(TeamRoom teamRoom) {
        return createRoom.execute(teamRoom);
    }

    @Override
    public List<TeamRoom> retrieveAllRooms() {
        return retrieveRooms.execute();
    }
}
