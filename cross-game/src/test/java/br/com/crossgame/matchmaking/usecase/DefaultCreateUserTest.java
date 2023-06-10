package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.usecase.DefaultCreateUserCommon;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@Disabled
class DefaultCreateUserTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    @Test
    void mustVerifyUserCreationData(){
        /*DefaultCreateUserCommon defaultCreateUserCommon = new DefaultCreateUserCommon(userRepository, passwordEncoder);

        when(defaultCreateUserCommon.execute(this.newUser())).thenReturn(this.newUser());

        verify(passwordEncoder, Mockito.times(1)).encode(this.newUser().getPassword());
        defaultCreateUserCommon.execute(this.newUser());
        verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));*/
    }

    private User newUser(){
        User userTest = new User();
        userTest.setUsername("teste");
        userTest.setEmail("teste@gmail.com");
        userTest.setPassword("Teste@134567");
        userTest.setRole(Role.ADMIN);
        return userTest;
    }
}
