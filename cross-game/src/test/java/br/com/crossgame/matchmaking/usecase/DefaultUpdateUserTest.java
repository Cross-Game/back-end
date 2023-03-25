package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;

@DataJpaTest
class DefaultUpdateUserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void mustUpdateUserWithId1(){
        User userTest = userRepository.save(this.updateUser());

        Assertions.assertEquals(1L, userTest.getId());
        Assertions.assertEquals("teste", userTest.getUsername());
        Assertions.assertEquals("teste@gmail.com", userTest.getEmail());
        Assertions.assertEquals(Role.ADMIN.name(), userTest.getRole().name());
    }

    private User updateUser(){
        User userTest = new User();
        userTest.setId(1L);
        userTest.setUsername("teste");
        userTest.setEmail("teste@gmail.com");
        userTest.setRole(Role.ADMIN);
        return userTest;
    }
}
