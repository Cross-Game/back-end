package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.usecase.DefaultRetrieveAllUsers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@Disabled
class DefaultRetrieveAllUsersTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void mustVerifyUserCreationData(){
        /*DefaultRetrieveAllUsers retrieveAllUsers = new DefaultRetrieveAllUsers(userRepository);

        when(retrieveAllUsers.execute()).thenReturn(this.users());

        retrieveAllUsers.execute();
        verify(userRepository, Mockito.times(1)).findAll();*/
    }

    private List<UserData> users(){
        UserData userTest1 = new UserData(2L,
                "Teste",
                "teste@gmail.com",
                Role.ADMIN,
                true);

        UserData userTest2 = new UserData(3L,
                "teste2",
                "teste2@gmail.com",
                Role.USER,
                false);

        List<UserData> users = new ArrayList<>();
        users.add(userTest1);
        users.add(userTest2);
        return users;
    }
}
