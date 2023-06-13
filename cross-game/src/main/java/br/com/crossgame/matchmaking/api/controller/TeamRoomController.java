package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.api.model.RoomData;
import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.internal.entity.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/team-rooms")
@CrossOrigin(maxAge = 3600)
@Api(tags = "Team room end-points")
public interface TeamRoomController {
    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create room", response = TeamRoom.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Room created")
    })
    RoomData createRoom(@Valid @RequestBody TeamRoom teamRoom,@PathVariable Long userId);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve all rooms", response = TeamRoom.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All rooms hava been listed"),
            @ApiResponse(code = 204, message = "We don't have registered rooms")
    })
    List<RoomData> retrieveAllRooms();

    @GetMapping("/{idRoom}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve all rooms", response = TeamRoom.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All rooms hava been listed"),
            @ApiResponse(code = 204, message = "We don't have registered rooms")
    })
    RoomData retrieveRoomById(@PathVariable Long idRoom);

    @PutMapping("/add-users/{userId}/{adminId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users add"),
            @ApiResponse(code = 400, message = "Error! You have wrong data"),
            @ApiResponse(code = 401, message = "Error! You are not ADMIN!")
    })
    void addUsersByadmin(@PathVariable Long userId,@PathVariable  Long adminId,@PathVariable Long roomId);
    @DeleteMapping("/remove-users/{userId}/{adminId}/{idRoom}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User removed"),
            @ApiResponse(code = 400, message = "Error! You have wrong data")
    })
    void removeUsersByAdmin(@PathVariable Long userId,@PathVariable  Long adminId,@PathVariable Long idRoom);

    @PutMapping("/add-users/user/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users add"),
            @ApiResponse(code = 400, message = "Error! You have wrong data")
    })
    void addUsersByUser(@PathVariable  Long userId,@PathVariable Long roomId);

    @DeleteMapping("/exit-room/{userId}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users add"),
            @ApiResponse(code = 400, message = "Error! You have wrong data")
    })
    void removeUserByUser(@PathVariable Long userId,@PathVariable  Long roomId);

    @GetMapping("/historic/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve all users", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All users have been listed"),
            @ApiResponse(code = 204, message = "We don't have registered users"),
            @ApiResponse(code = 404, message = "Room not found!")
    })
    List<UserData> retrieveHistoricOfUsers(@PathVariable Long roomId);


    @PutMapping("/invite-request/{userId}/{notificationId}/{response}/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Response invite", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Answered invitation "),
            @ApiResponse(code = 204, message = "We don't have invites")
    })
    void responseNotify(@PathVariable NotificationState response, @PathVariable Long userId,
                        @PathVariable Long notificationId, @PathVariable Long roomId);
}
