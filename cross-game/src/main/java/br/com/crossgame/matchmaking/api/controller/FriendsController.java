package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserAndFriend;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/friends")
@CrossOrigin(maxAge = 3600)
@Api(tags = "Friends end-points")
public interface FriendsController {

    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Send a friend request")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Friend request sended"),
            @ApiResponse(code = 404, message = "User to send request not found"),
            @ApiResponse(code = 404, message = "This username does not exists"),
            @ApiResponse(code = 400, message = "You cannot add yourself as a friend"),
            @ApiResponse(code = 409, message = "You already has this user as a friend")
    })
    UserAndFriend addFriendToAnUser(@PathVariable Long userId, @RequestBody Friend friendToAdd);

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve all friend by user id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Friends listed"),
            @ApiResponse(code = 204, message = "This user dont have friends"),
            @ApiResponse(code = 404, message = "User not found")
    })
    List<Friend> retrieveAllFriendsByUserId(@PathVariable Long userId);

    @DeleteMapping(path = "/{userId}/{friendUsername}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an friend")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Friend deleted"),
            @ApiResponse(code = 404, message = "Friend not found")
    })
    void deleteFriend(@PathVariable Long userId, @PathVariable String friendUsername);

    @GetMapping(path = "/{userId}/{archiveType}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve friends in a .csv file")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Friend request sended"),
            @ApiResponse(code = 404, message = "Friend not found")
    })
    List<Friend> retrieveAllFriendsByUserIdAndExportToCsvOrTxt(@PathVariable Long userId, @PathVariable String archiveType);

    @PatchMapping(path = "/confirming-friend-request/{userId}/{friendUsername}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Confirm friend request")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Friend request accepted"),
            @ApiResponse(code = 404, message = "You dont have any friend request from this username")
    })
    UserAndFriend confirmFriendRequest(@PathVariable Long userId, @PathVariable String friendUsername);

    @DeleteMapping(path = "/declining-friend-request/{userId}/{friendUsername}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Decline friend request")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Friend request denied"),
            @ApiResponse(code = 404, message = "You dont have any friend request from this username")
    })
    void decliningFriendRequest(@PathVariable Long userId, @PathVariable String friendUsername);

}
