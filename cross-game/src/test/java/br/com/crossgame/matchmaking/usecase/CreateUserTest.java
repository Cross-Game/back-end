package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

@DataJpaTest
class CreateUserTest {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private User user;

    @Test
    @Transactional
    void mustVerifyUserCreationData(){
        User verifyUser = userRepository.save(this.newUser());
        Assertions.assertEquals(2L, verifyUser.getId());
        Assertions.assertEquals("teste", verifyUser.getUsername());
        Assertions.assertEquals("teste@gmail.com", verifyUser.getEmail());
        Assertions.assertEquals(Role.ADMIN.name(), verifyUser.getRole().name());
    }

    private User newUser(){
        User userTest = new User();
        userTest.setId(2L);
        userTest.setUsername("teste");
        userTest.setEmail("teste@gmail.com");
        userTest.setPassword("Teste@134");
        userTest.setRole(Role.ADMIN);
        return userTest;
    }
}
