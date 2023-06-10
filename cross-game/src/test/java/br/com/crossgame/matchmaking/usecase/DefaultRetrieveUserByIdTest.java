package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.usecase.DefaultRetrieveUserById;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Disabled
class DefaultRetrieveUserByIdTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void mustVerifyAnUserWithIdEquals1(){
        DefaultRetrieveUserById retrieveUserById = new DefaultRetrieveUserById(userRepository);

        Long idUser = 10000L;
        User user = Mockito.mock(User.class);
        when(user.getUsername()).thenReturn("teste");
        when(user.getEmail()).thenReturn("teste@email.com");
        when(user.getPassword()).thenReturn("Teste@1234567");
        when(user.getRole()).thenReturn(Role.USER);

        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(idUser)).thenReturn(optionalUser);

        retrieveUserById.execute(idUser);
        Mockito.verify(userRepository, Mockito.times(1)).findById(ArgumentMatchers.any(Long.class));
    }
}
