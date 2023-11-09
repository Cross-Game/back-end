package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ResponseNotify;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.NotificationRepository;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DefaultResponseNotify implements ResponseNotify {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private TeamRoomRepository teamRoomRepository;
    @Override
    public void execute(NotificationState response, Long userId, Long notificationId, Long roomId) {
        User user = userRepository.findById(userId).get();
        TeamRoom teamRoom = teamRoomRepository.findById(roomId).get();
        this.validateData(user, teamRoom);

        Notification notificationUser = user.getNotifies().stream()
                .filter(notification -> notification.getId().equals(notificationId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Notification not found"));

        if (response.equals(NotificationState.APPROVED)){
            notificationUser.setNotificationState(NotificationState.APPROVED);
            teamRoom.getUsersInRoom().add(user);
        }
        if (response.equals(NotificationState.CANCELLED)){
            notificationUser.setNotificationState(NotificationState.CANCELLED);
        }
        if (response.equals(NotificationState.AWAITING)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This notification is on Awaiting");
        }
        notificationRepository.save(notificationUser);
        teamRoomRepository.save(teamRoom);
    }

    private void validateData(User user, TeamRoom teamRoom) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found !");
        }
        if (teamRoom == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found !");
        }
    }
}
