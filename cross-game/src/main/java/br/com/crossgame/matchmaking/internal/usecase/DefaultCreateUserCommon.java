package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.CreateUser;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultCreateUserCommon implements CreateUser {
    private UserRepository userRepository;

    @Override
    @Transactional
    public User execute(User user) {
        user.setId(null);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        log.info("Creating user: " + user);
        this.configUser(user);
        return userRepository.save(user);
    }
    private void configUser(User user) {
        String medalhaInicial = "PRATA";
        Integer pontuacaoInicial = 100;
        String titulo = "INICIANTE";
        log.info(String.format("Medalha : %s" +
                "Pontuacao : %d" +
                "Titulo : %s", medalhaInicial, pontuacaoInicial, titulo));

    }

}
