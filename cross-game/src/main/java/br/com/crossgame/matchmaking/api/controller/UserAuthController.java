package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.Token;
import br.com.crossgame.matchmaking.internal.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Controladora de Autenticação", description = "Realiza a geração de um token de acesso")
@RestController
@RequestMapping(path = "/user-auth")
public interface UserAuthController {

    @ApiOperation(value = "Gera um token para autenticação do usuario", response = String.class)
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Token retrieveToken(@RequestBody User user);
}
