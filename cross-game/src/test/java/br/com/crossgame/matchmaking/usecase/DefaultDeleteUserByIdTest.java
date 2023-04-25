package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.usecase.DefaultDeleteUserById;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class DefaultDeleteUserByIdTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void mustDeleteAnUser(){
        DefaultDeleteUserById defaultDeleteUserById = new DefaultDeleteUserById(userRepository);

        Long idUser = 10000L;
        User user = Mockito.mock(User.class);
        when(user.getUsername()).thenReturn("teste");
        when(user.getEmail()).thenReturn("teste@email.com");
        when(user.getPassword()).thenReturn("Teste@1234567");
        when(user.getRole()).thenReturn(Role.USER);

        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(idUser)).thenReturn(optionalUser);

        defaultDeleteUserById.execute(idUser);
        Mockito.verify(userRepository, Mockito.times(1)).delete(ArgumentMatchers.any(User.class));
    }
}
