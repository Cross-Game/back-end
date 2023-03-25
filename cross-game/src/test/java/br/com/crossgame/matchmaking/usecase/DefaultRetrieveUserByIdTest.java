package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
class DefaultRetrieveUserByIdTest {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private User user;

    @Test
    void mustVerifyAnUserWithIdEquals1(){
        User userTest = userRepository.findById(1L)
                .stream()
                .findFirst()
                .orElseThrow(null);

        Assertions.assertEquals(1L, userTest.getId());
        Assertions.assertEquals("HOliveira", userTest.getUsername());
        Assertions.assertEquals("holiveira@gmail.com", userTest.getEmail());
    }
}
