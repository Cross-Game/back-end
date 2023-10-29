package br.com.crossgame.matchmaking;

import br.com.crossgame.matchmaking.api.model.TokenResponse;
import br.com.crossgame.matchmaking.internal.usecase.DefaultGenerateTokenIGBD;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DefaultGenerateTokenIGBDTest {

    @Autowired
    DefaultGenerateTokenIGBD generateTokenIGBD;

    @Test
    void testExecute(){
        TokenResponse tokenResponse = this.generateTokenIGBD.execute();
        Assertions.assertNotNull(tokenResponse);
    }
}
