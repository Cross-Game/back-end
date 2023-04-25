package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllUsers;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllUsers implements RetrieveAllUsers {

    private UserRepository userRepository;

    @Override
    public List<UserData> execute() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return this.convertUserToUserData(users);
    }

    private List<UserData> convertUserToUserData(List<User> users){
        List<UserData> userData = new ArrayList<>();
        for (User user : users){
            userData.add(new UserData(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole(),
                    user.isOnline()));
        }
        return userData;
    }
}
