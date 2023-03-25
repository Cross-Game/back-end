package br.com.crossgame.matchmaking.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.security.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Test
    void mustRetrieveAnToken(){
        User user = new User();
        user.setId(100L);
        user.setUsername("TESTE");
        user.setPassword("Teste@1234567");
        user.setEmail("teste@gmail.com");
        user.setRole(Role.USER);

        jwtService.setSubscriptionKey("Q3JvJDUtNmFtMy1NYTdjaE1AazFuNg==");
        jwtService.setTokenExpiration("180");

        String token = jwtService.generateToken(user);

        Assertions.assertNotNull(token);
    }
}
