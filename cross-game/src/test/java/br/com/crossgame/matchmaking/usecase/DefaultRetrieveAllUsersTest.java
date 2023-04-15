package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.usecase.DefaultRetrieveAllUsers;
import br.com.crossgame.matchmaking.internal.usecase.DefaultUpdateUser;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
class DefaultRetrieveAllUsersTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void mustVerifyUserCreationData(){
        DefaultRetrieveAllUsers retrieveAllUsers = new DefaultRetrieveAllUsers(userRepository);

        when(retrieveAllUsers.execute()).thenReturn(this.users());

        retrieveAllUsers.execute();
        verify(userRepository, Mockito.times(1)).findAll();
    }

    private List<User> users(){
        User userTest1 = new User();
        userTest1.setId(2L);
        userTest1.setUsername("teste");
        userTest1.setEmail("teste@gmail.com");
        userTest1.setPassword("Teste@13412332");
        userTest1.setRole(Role.ADMIN);

        User userTest2 = new User();
        userTest2.setId(3L);
        userTest2.setUsername("teste2");
        userTest2.setEmail("teste2@gmail.com");
        userTest2.setPassword("Teste2@13412332");
        userTest2.setRole(Role.USER);

        List<User> users = new ArrayList<>();
        users.add(userTest1);
        users.add(userTest2);
        return users;
    }
}
