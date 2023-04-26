package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserAndFriend;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/friends")
public interface FriendsController {

    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    UserAndFriend addFriendToAnUser(@PathVariable Long userId, @RequestBody Friend friendToAdd);

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<Friend> retrieveAllFriendsByUserId(@PathVariable Long userId);

    @DeleteMapping(path = "/{userId}/{friendUsername}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteFriend(@PathVariable Long userId, @PathVariable String friendUsername);

    @GetMapping(path = "/{userId}/{archiveType}")
    @ResponseStatus(HttpStatus.OK)
    List<Friend> retrieveAllFriendsByUserIdAndExportToCsvOrTxt(@PathVariable Long userId, @PathVariable String archiveType);

    @PatchMapping(path = "/confirming-friend-request/{userId}/{friendUsername}")
    @ResponseStatus(HttpStatus.OK)
    UserAndFriend confirmFriendRequest(@PathVariable Long userId, @PathVariable String friendUsername);

    @DeleteMapping(path = "/declining-friend-request/{userId}/{friendUsername}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void decliningFriendRequest(@PathVariable Long userId, @PathVariable String friendUsername);

}
