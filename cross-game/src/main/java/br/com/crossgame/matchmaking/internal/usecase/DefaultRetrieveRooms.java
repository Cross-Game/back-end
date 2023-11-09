package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.RoomData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveRooms;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultRetrieveRooms implements RetrieveRooms {

    private TeamRoomRepository teamRoomRepository;

    @Override
    public List<RoomData> execute() {
        List<RoomData> roomData = new ArrayList<>();
        teamRoomRepository.findAll().forEach(teamRoom ->{
            roomData.add(convert(teamRoom));
        });
        return roomData;
    }

    public static RoomData convert(TeamRoom room){
        if (room != null){
            return new RoomData(room.getId(),room.getRoomName(), room.getCapacity(),room.getGameName(),room.getRankGame(),
                    room.getLevelGame(),DefaultRetrieveAllUsers.convertUserToUserData(room.getUsersInRoom()),
                    room.isPrivateRoom(),room.getTokenAccess(),room.getDescription(), room.isTerminated(), room.getIdUserAdmin() );
        }
        throw new RuntimeException("This room is null!");
    }

}
