package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserAuthController;
import br.com.crossgame.matchmaking.api.usecase.AuthenticateUser;
import br.com.crossgame.matchmaking.internal.entity.Token;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@ConditionalOnSingleCandidate(UserAuthController.class)
public class DefaultUserAuthController implements UserAuthController {

    private AuthenticateUser authenticateUser;
    private JwtService jwtService;

    @Override
    public Token retrieveToken(User user) {
            User userFound = this.authenticateUser.execute(user);
            String token = jwtService.generateToken(userFound);
            return new Token(token);
    }
}
