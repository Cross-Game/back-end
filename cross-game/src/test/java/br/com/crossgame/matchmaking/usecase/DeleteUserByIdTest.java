package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.Optional;

@DataJpaTest
class DeleteUserByIdTest {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private User user;

    @Test
    @Transactional
    void mustDeleteAnUser(){
        userRepository.deleteById(1L);
        Optional<User> userToRemove = userRepository.findById(1L);
        Assertions.assertEquals(Optional.empty(), userToRemove);
    }
}
