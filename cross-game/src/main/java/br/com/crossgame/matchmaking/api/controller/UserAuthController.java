package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.Token;
import br.com.crossgame.matchmaking.internal.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user-auth")
@Api(tags = "JWT Token", description = "Generate JWT token")
public interface UserAuthController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve authorization token", response = Token.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User authenticated"),
            @ApiResponse(code = 404, message = "Username not found")
    })
    public Token retrieveToken(@RequestBody User user);
}
