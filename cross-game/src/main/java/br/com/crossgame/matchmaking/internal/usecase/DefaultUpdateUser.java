package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserCreate;
import br.com.crossgame.matchmaking.api.usecase.UpdateUser;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class DefaultUpdateUser implements UpdateUser {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User execute(UserCreate userCreate) {
        String passwordEncoded = passwordEncoder.encode(userCreate.password());
        User userUpdated = new User(userCreate.id(),
                userCreate.username(),
                userCreate.email(),
                passwordEncoded,
                true,
                userCreate.role());

        log.info("Updating user: " + userUpdated.getUsername());
        return userRepository.save(userUpdated);
    }
}
