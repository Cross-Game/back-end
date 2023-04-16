package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.Friends;
import br.com.crossgame.matchmaking.internal.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Controladora de Amigos", description = "Realiza operações com os amigos")
@RestController
@RequestMapping(path = "/friends")
public interface FriendsController {

    @ApiOperation(value = "Adiciona um amigo ao usuario", response = String.class)
    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    User addFriendToAnUser(@PathVariable Long userId, @RequestBody Friends friendToAdd);

    @ApiOperation(value = "Realiza a busca de todos os amigos de um usuario", response = String.class)
    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<Friends> retrieveAllFriendsByUserId(@PathVariable Long userId);

    @ApiOperation(value = "Remove um amigo de um usuario", response = String.class)
    @DeleteMapping(path = "/{userId}/{friendUserName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteFriend(@PathVariable Long userId, @PathVariable String friendUserName);
}
