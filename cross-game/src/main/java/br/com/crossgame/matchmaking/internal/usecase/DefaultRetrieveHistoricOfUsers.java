package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllUsers;
import br.com.crossgame.matchmaking.api.usecase.RetrieveHistoricOfUsers;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveHistoricOfUsers implements RetrieveHistoricOfUsers {
    private UserRepository userRepository;
    private TeamRoomRepository roomRepository;
    @Override
    public List<UserData> execute(Long idRoom) {
        if (!roomRepository.existsById(idRoom)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This room don't exists!");
        }
        List<User> users = new ArrayList<>();
        roomRepository.findById(idRoom).orElseThrow().getUsersHistoryId().forEach(usersId ->{
          User user =  userRepository.findById(usersId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"This user don't exists!"));
          users.add(user);
        });
        return new ArrayList<>(DefaultRetrieveAllUsers.convertUserToUserData(users));
    }
}
