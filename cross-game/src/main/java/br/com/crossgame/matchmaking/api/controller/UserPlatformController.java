package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;
import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user-platforms")
@CrossOrigin(maxAge = 3600)
@Api(tags = "User platforms end-points")
public interface UserPlatformController {

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve platforms from a user", response = ArrayList.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All games platforms owned by a user have been listed"),
            @ApiResponse(code = 204, message = "This user does not have registered games platforms yet"),
            @ApiResponse(code = 404, message = "User not found")
    })
    List<GameplayPlatformType> retrieveLinkedGamePlatformsByUserId(@PathVariable Long userId);

    @PatchMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update user platforms", response = ArrayList.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User games platforms update"),
            @ApiResponse(code = 404, message = "User not found")
    })
    List<GameplayPlatformType> updateGamePlatformsForUserById(@PathVariable Long userId, @RequestBody List<GameplayPlatformType> platforms);
}
