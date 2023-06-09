package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.AddUserByUser;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultAddUserByUser implements AddUserByUser {
    private UserRepository userRepository;
    private TeamRoomRepository teamRoomRepository;

    @Override
    public void execute(Long userId, Long roomId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        this.validateData(user, roomId);
        this.validateRoom(roomId, user);
        TeamRoom teamRoom = teamRoomRepository.findById(roomId).orElseThrow();

        teamRoom.getUsersInRoom().add(user);
        teamRoom.getUsersHistoryId().add(userId);
        teamRoomRepository.save(teamRoom);
        log.info("user added in room!");
    }

    private void validateData(User user, Long roomId) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your request don't have users.");
        }
        if (!teamRoomRepository.existsById(roomId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found.");
        }
    }

    private void validateRoom(Long roomId, User user) {
        TeamRoom teamRoom = teamRoomRepository.findById(roomId).orElseThrow();
        if (teamRoom.getUsersInRoom().size() >= teamRoom.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This room is full");
        }
        if (teamRoom.isPrivate()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This room is Private");
        }
    }
}
