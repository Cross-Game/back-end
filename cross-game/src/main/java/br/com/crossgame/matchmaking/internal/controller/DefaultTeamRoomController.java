package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.TeamRoomController;
import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.api.model.RoomData;
import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.NotificationState;
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
    private AddUsers addUsersByAdmin;
    private AddUserByUser addUserByUser;
    private RemoveUserByAdmin removeUserByAdmin;
    private RemoveUserByUser removeUserByUser;
    private RetrieveHistoricOfUsers retrieveHistoricOfUsers;

    @Override
    public RoomData createRoom(TeamRoom teamRoom, Long userId) {
        return createRoom.execute(teamRoom, userId);
    }

    @Override
    public List<RoomData> retrieveAllRooms() {
        return retrieveRooms.execute();
    }

    @Override
    public void addUsersByadmin(Long userId, Long adminId, Long roomId) {
        addUsersByAdmin.execute(userId, adminId, roomId);
    }

    @Override
    public void removeUsersByAdmin(Long userId, Long adminId, Long idRoom) {
        removeUserByAdmin.execute(userId, adminId, idRoom);
    }

    @Override
    public void addUsersByUser(Long userId, Long roomId) {
        addUserByUser.execute(userId, roomId);
        //Adicionar o usuário na sala somente se for publica
        //se for privada vc n adiciona e lança um forbidden
        //Ok Finalizado !!!!! Só ajustar a notificação
    }

    @Override
    public void removeUserByUser(Long userId, Long roomId) {
        removeUserByUser.execute(userId, roomId);
        //o usuário pode se excluir da sala
        //Ok Usuario comun pode sair da sala sem problemas !!
    }

    @Override
    public List<UserData> retrieveHistoricOfUsers(Long roomId) {
        return retrieveHistoricOfUsers.execute(roomId);
    }

    @Override
    public void responseNotify(NotificationState response, Long userId, Long notificationId) {
        //validar antes se a notificação está como state awaiting
        //response vai definir se ele aceitou entrar ou não
        //atualizar de acordo com a resposta da notificação
    }


}
