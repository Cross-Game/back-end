package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveAnExistentUserIdForLoginServices;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DefaultRetrieveAnExistentUserIdForLoginServices implements RetrieveAnExistentUserIdForLoginServices {

    private UserRepository userRepository;

    @Override
    public Long execute(String username, String email) {
        User user = this.userRepository.findByUsernameAndEmail(username,
                email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User and email not found"));

        return user.getId();
    }
}
