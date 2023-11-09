package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RemoveUserByAdmin;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultRemoveUserByAdmin implements RemoveUserByAdmin {
    private final UserRepository userRepository;
    private final TeamRoomRepository teamRoomRepository;

    @Override
    public void execute(Long userId, Long adminId, Long idRoom) {
        this.validate(userId, adminId, idRoom);
        TeamRoom teamRoom = teamRoomRepository.findById(idRoom).orElseThrow();
        Iterator<User> iterator = teamRoom.getUsersInRoom().iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(userId)) {
                iterator.remove();
                log.info("User removed!");
            }
        }
    }

    private void validate(Long userId, Long adminId, Long idRoom) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
        if (!teamRoomRepository.existsById(idRoom)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found!");
        }
        TeamRoom teamRoom = teamRoomRepository.findById(idRoom).get();
        if (!teamRoom.getIdUserAdmin().equals(adminId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not ADMIN!");
        }
        boolean userOutRoom = teamRoom.getUsersInRoom().stream().filter(user -> user.getId().equals(userId)).toList().isEmpty();
        if (userOutRoom) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found in this room!");
        }

    }
}
