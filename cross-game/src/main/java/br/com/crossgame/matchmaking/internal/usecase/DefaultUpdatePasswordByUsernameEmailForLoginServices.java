package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserDataForLoginServices;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAnExistentUserIdForLoginServices;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.api.usecase.UpdatePasswordByUsernameEmailForLoginServices;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdatePasswordByUsernameEmailForLoginServices
        implements UpdatePasswordByUsernameEmailForLoginServices {

        private RetrieveAnExistentUserIdForLoginServices retrieveAnExistentUserIdForLoginServices;

        private RetrieveUserById retrieveUserById;

        private UserRepository userRepository;

        private PasswordEncoder passwordEncoder;

    @Override
    public void execute(UserDataForLoginServices userDataForLoginServices) {
        Long userId = this.retrieveAnExistentUserIdForLoginServices.execute(userDataForLoginServices.username(),
                userDataForLoginServices.email());
        User userToUpdatePassword = this.retrieveUserById.execute(userId);

        userToUpdatePassword.setPassword(this.passwordEncoder.encode(userDataForLoginServices.password()));
        this.userRepository.save(userToUpdatePassword);
    }
}
