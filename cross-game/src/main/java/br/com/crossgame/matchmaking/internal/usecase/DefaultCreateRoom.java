package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.CreateRoom;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import io.jsonwebtoken.lang.Assert;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.rmi.NoSuchObjectException;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultCreateRoom implements CreateRoom {

    private final TeamRoomRepository teamRoomRepository;

    @Override
    public TeamRoom execute(TeamRoom teamRoom) {
        this.validateRoom(teamRoom);
        if (teamRoom.isPrivate()){
            teamRoom.setTokenAccess(this.generateAccessToken());
        }
        TeamRoom teamRoomSaved = teamRoomRepository.save(teamRoom);
        return teamRoomSaved;
    }

    private void validateRoom(TeamRoom teamRoom){
        if (Objects.isNull(teamRoom)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This value is null!");
        }
        Assert.notNull(teamRoom.getRoomName());
        Assert.notNull(teamRoom.getGameName());
        log.info("Room has valid datas");
    }

    private String generateAccessToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
