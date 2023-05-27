package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/team-rooms")
@CrossOrigin(maxAge = 3600)
@Api(tags = "Team room end-points")
public interface TeamRoomController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create room", response = TeamRoom.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Room created")
    })
    TeamRoom createRoom(@Valid @RequestBody TeamRoom teamRoom);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve all rooms", response = TeamRoom.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All rooms hava been listed"),
            @ApiResponse(code = 204, message = "We don't have registered rooms")
    })
    List<TeamRoom> retrieveAllRooms();
}
