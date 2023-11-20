package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserCreate;
import br.com.crossgame.matchmaking.api.usecase.CreateUser;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultCreateUserCommon implements CreateUser {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User execute(UserCreate user) {
        if (checkUserAlreadyExists(user.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This user Already exist");
        }
        String cryptedPassword = passwordEncoder.encode(user.password());
        log.info("Creating user: " + user);
        User creatingUser = new User(user.username(),
                user.email(),
                cryptedPassword,
                false,
                user.role());
        return userRepository.save(creatingUser);
    }

    private Boolean checkUserAlreadyExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}