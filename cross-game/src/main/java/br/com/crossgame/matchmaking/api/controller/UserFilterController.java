package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users-filter")
@Api(tags = "User filter end-points")
public interface UserFilterController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve users with filters", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All users have been listed"),
            @ApiResponse(code = 204, message = "We don't have registered users yet")
    })
    List<UserData> retrieveAllUsersWithFilter(@RequestParam(required = false, name = "skillLevel") SkillLevel skillLevel,
                                              @RequestParam(required = false, name = "gameFunction") GameFunction gameFunction,
                                              @RequestParam(required = false, name = "gameName") String gameName,
                                              @RequestParam(required = false, name = "gameGenre") GameGenre gameGenre,
                                              @RequestParam(required = false, name = "preference") Preferences preferences,
                                              @RequestParam(required = false, name = "preference2") Preferences preferences2,
                                              @RequestParam(required = false, name = "preference3") Preferences preferences3,
                                              @RequestParam(required = false, name = "skillLevelFeedback", defaultValue = "false")
                                              boolean skillLevelFeedback,
                                              @RequestParam(required = false, name = "behaviorFeedback", defaultValue = "true")
                                              boolean behaviorFeedback);
}
