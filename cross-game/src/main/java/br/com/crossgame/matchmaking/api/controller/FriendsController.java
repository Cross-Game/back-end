package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/friends")
public interface FriendsController {

    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    User addFriendToAnUser(@PathVariable Long userId, @RequestBody Friend friendToAdd);

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<Friend> retrieveAllFriendsByUserId(@PathVariable Long userId);

    @DeleteMapping(path = "/{userId}/{friendUserName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteFriend(@PathVariable Long userId, @PathVariable String friendUserName);

    @GetMapping(path = "/{userId}/{archiveType}")
    @ResponseStatus(HttpStatus.OK)
    List<Friend> retrieveAllFriendsByUserIdAndExportToCsvOrTxt(@PathVariable Long userId, @PathVariable String archiveType);
}
