package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.Token;
import br.com.crossgame.matchmaking.internal.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user-auth")
public interface UserAuthController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Token retrieveToken(@RequestBody User user);
}
