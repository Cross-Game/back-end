package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.AddUsers;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.atomic.AtomicBoolean;


@Service
@Slf4j
@AllArgsConstructor
public class DefaultAddUsers implements AddUsers {
    private UserRepository userRepository;
    private TeamRoomRepository teamRoomRepository;

    @Override
    public void execute(Long userId, Long adminId, Long roomId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        this.validateData(user, adminId, roomId);
        this.validateRoom(roomId, adminId, user);
        TeamRoom teamRoom = teamRoomRepository.findById(roomId).orElseThrow();

        teamRoom.getUsersInRoom().add(user);
        teamRoom.getUsersHistoryId().add(userId);
        teamRoomRepository.save(teamRoom);
        log.info("user added in room!");

    }

    private void validateData(User user, Long adminId, Long roomId) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your request don't have users.");
        }
        if (!userRepository.existsById(adminId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        if (!teamRoomRepository.existsById(roomId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found.");
        }
    }

    private void validateRoom(Long roomId, Long adminId, User user) {
        TeamRoom teamRoom = teamRoomRepository.findById(roomId).orElseThrow();
        User userAdmin = userRepository.findById(adminId).orElseThrow();
//        if (teamRoom.getUsersInRoom().size() >= teamRoom.getCapacity()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This room is full");
//        }
        if (!teamRoom.getIdUserAdmin().equals(userAdmin.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You is not ADMIN in this room");
        }
        if (teamRoom.isPrivateRoom()) {
            AtomicBoolean hasInvite = new AtomicBoolean(false);
            user.getNotifies().stream().filter(notification -> notification.getNotificationType()
                            .equals(NotificationType.GROUP_INVITE)).toList().stream()
                    .forEach(notification -> {
                        if (notification.getDescription().equals(teamRoom.getTokenAccess())
                                && notification.getNotificationState().equals(NotificationState.APPROVED)) {
                            log.info("User accepted!");
                            hasInvite.set(true);
                        }
                    });
            if (!hasInvite.get()){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have permission!");
            }
        }
    }
}
