package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.usecase.DefaultUpdateUser;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultUpdateUserTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    @Test
    void mustUpdateAnUser(){
        DefaultUpdateUser updateUser = new DefaultUpdateUser(userRepository, passwordEncoder);

        when(updateUser.execute(this.userUpdate())).thenReturn(this.userUpdate());

        updateUser.execute(this.userUpdate());
        verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    private User userUpdate(){
        User userTest = new User();
        userTest.setId(2L);
        userTest.setUsername("teste");
        userTest.setEmail("teste@gmail.com");
        userTest.setPassword("Teste@13412332");
        userTest.setRole(Role.ADMIN);
        return userTest;
    }
}
