package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserGameCreate;
import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.model.UsernameResponse;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user-games")
@CrossOrigin(maxAge = 3600)
@Api(tags = "User game end-points")
public interface UserGameController {

    @PostMapping(path = "/{gameId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Link game to a user", response = UserGame.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Game linked to a user")
    })
    UserGameResponse linkGameToUser(@RequestBody UserGameCreate userGameCreate,
                                    @PathVariable Long gameId,
                                    @PathVariable Long userId);

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve games from a user", response = ArrayList.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All games owned by a user have been listed"),
            @ApiResponse(code = 204, message = "This user does not have registered games yet"),
            @ApiResponse(code = 404, message = "User not found")
    })
    List<UserGameResponse> retrieveLinkedGamesByUserId(@PathVariable Long userId);

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update linked game to a user", response = UserGame.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "User game updated"),
            @ApiResponse(code = 404, message = "UserGame to update not found")
    })
    UserGameResponse updateLinkedGameToUser(@RequestBody UserGameCreate userGame);

    @DeleteMapping(path = "/{userId}/{userGameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a linked game to user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User game deleted"),
            @ApiResponse(code = 404, message = "User or linked game not found")
    })
    void deleteLinkedGameToUser(@PathVariable Long userId, @PathVariable Long userGameId);

    @GetMapping(path = "/{userId}/{username}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Validate username", response = UsernameResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "user validated"),
            @ApiResponse(code = 204, message = "This user does not have registered username yet"),
            @ApiResponse(code = 404, message = "Username not found")
    })
    ResponseEntity<UsernameResponse> validateUsername(@PathVariable Long userId, @PathVariable String username);
}
