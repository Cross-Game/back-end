package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.RoomData;
import br.com.crossgame.matchmaking.api.usecase.CreateRoom;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultCreateRoom implements CreateRoom {

    private final TeamRoomRepository teamRoomRepository;
    private final UserRepository userRepository;

    @Override
    public RoomData execute(TeamRoom teamRoom, Long userId) {
        this.validateRoom(teamRoom,userId);
        if (teamRoom.isPrivateRoom()){
            teamRoom.setTokenAccess(this.generateAccessToken());
        }
        teamRoom.setIdUserAdmin(userId);
        teamRoom.getUsersInRoom().add(userRepository.findById(userId).get());
        teamRoom.getUsersHistoryId().add(userId);

        TeamRoom teamRoomSaved = teamRoomRepository.save(teamRoom);

        return DefaultRetrieveRooms.convert(teamRoomSaved);
    }

    private void validateRoom(TeamRoom teamRoom,Long idAdmin){
        if (Objects.isNull(teamRoom)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This value is null!");
        }
        if(!userRepository.existsById(idAdmin)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!");
        }
    }

    private String generateAccessToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
