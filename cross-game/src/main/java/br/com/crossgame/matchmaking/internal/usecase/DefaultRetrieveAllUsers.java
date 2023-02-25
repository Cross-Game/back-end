package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetriveAllUsers;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllUsers implements RetriveAllUsers {

    private UserRepository userRepository;

    @Override
    public List<User> execute() {
        return userRepository.findAll();
    }
}
