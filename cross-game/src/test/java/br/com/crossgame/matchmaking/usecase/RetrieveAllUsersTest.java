package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
class RetrieveAllUsersTest {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private User user;

    @Test
    void mustVerifyUserCreationData(){
        User verifyUser = userRepository.findById(1L)
                .stream()
                .findFirst()
                .orElseThrow(null);
        User verifyUser2 = userRepository.save(this.newUser());


        Assertions.assertEquals(1L, verifyUser.getId());
        Assertions.assertEquals("HOliveira", verifyUser.getUsername());
        Assertions.assertEquals("holiveira@gmail.com", verifyUser.getEmail());
        Assertions.assertEquals(2L, verifyUser2.getId());
        Assertions.assertEquals("teste", verifyUser2.getUsername());
        Assertions.assertEquals("teste@gmail.com", verifyUser2.getEmail());

    }

    private User newUser(){
        User userTest = new User();
        userTest.setId(2L);
        userTest.setUsername("teste");
        userTest.setEmail("teste@gmail.com");
        userTest.setPassword("Teste@13412332");
        userTest.setRole(Role.ADMIN);
        return userTest;
    }
}
