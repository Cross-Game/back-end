package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserCreate;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
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

    private RetrieveUserById retrieveUserById;

    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User execute(UserCreate userCreate) {
        String passwordEncoded = passwordEncoder.encode(userCreate.password());
        User userToUpdate = this.retrieveUserById.execute(userCreate.id());
        userToUpdate.setId(userCreate.id());
        userToUpdate.setUsername(userCreate.username());
        userToUpdate.setEmail(userCreate.email());
        userToUpdate.setPassword(passwordEncoded);
        userToUpdate.setOnline(true);
        userToUpdate.setRole(userCreate.role());

        log.info("Updating user: " + userToUpdate.getUsername());
        return userRepository.save(userToUpdate);
    }
}
