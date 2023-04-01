package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.usecase.DefaultAuthenticateUser;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

class DefaultAuthenticateUserTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    @Test
    void mustAuthenticateAUser(){
        DefaultAuthenticateUser authenticateUser = new DefaultAuthenticateUser(passwordEncoder, userRepository);

        User user = Mockito.mock(User.class);
        when(user.getId()).thenReturn(10000L);
        when(user.getUsername()).thenReturn("testeAuth");
        when(user.getEmail()).thenReturn("teste@email.com");
        when(user.getPassword()).thenReturn("Teste@1234567");
        when(user.getRole()).thenReturn(Role.USER);

        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByUsername(anyString())).thenReturn(optionalUser);
        when(passwordEncoder.matches(anyString(), anyString()))
                .thenReturn(true);

        userRepository.save(user);
        authenticateUser.execute(user);
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
        verify(userRepository, times(2)).findByUsername(ArgumentMatchers.any(String.class));
    }
}
