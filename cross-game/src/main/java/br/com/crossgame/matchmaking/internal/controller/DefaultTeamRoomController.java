package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.TeamRoomController;
import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.api.model.RoomData;
import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.entity.User;
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
    private AddUsers addUsersByAdmin;
    private AddUserByUser addUserByUser;
    private RemoveUserByAdmin removeUserByAdmin;
    private RemoveUserByUser removeUserByUser;
    private RetrieveHistoricOfUsers retrieveHistoricOfUsers;
    @Override
    public RoomData createRoom(TeamRoom teamRoom, Long userId) {
        return createRoom.execute(teamRoom,userId);
    }

    @Override
    public List<RoomData> retrieveAllRooms() {
        return retrieveRooms.execute();
    }

    @Override
    public void addUsersByadmin(Long userId, Long adminId,Long roomId) {
    addUsersByAdmin.execute(userId,adminId,roomId);
    }

    @Override
    public void removeUsersByAdmin(Long userId, Long adminId,Long idRoom) {
        removeUserByAdmin.execute(userId,adminId,idRoom);
    }

    @Override
    public void addUsersByUser(Long userId, Long roomId) {
        addUserByUser.execute(userId,roomId);
    }

    @Override
    public void removeUserByUser(Long userId, Long roomId) {
        removeUserByUser.execute(userId,roomId);
    }

    @Override
    public List<UserData> retrieveHistoricOfUsers(Long roomId) {
        return retrieveHistoricOfUsers.execute(roomId);
    }

    @Override
    public List<User> retrieveAllUsersInRoom(Long roomId) {
        return null;
    }

    @Override
    public void responseNotify(NotificationResponse response, Long userId, Long notificationId) {

    }


}
