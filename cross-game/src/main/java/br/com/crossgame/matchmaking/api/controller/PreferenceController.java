package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.model.UserAndPreferenceResponse;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/preferences")
@CrossOrigin(maxAge = 3600)
@Api(tags = "Preference end-points")
public interface PreferenceController {

    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user preferences", response = UserAndPreference.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "User preferences created")
    })
    UserAndPreference createPreferenceForUserById(@PathVariable Long userId, @RequestBody Preference preference);

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve user preferences", response = UserAndPreference.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User preferences updated"),
            @ApiResponse(code = 404, message = "User not found")
    })
    UserAndPreferenceResponse retrieveUserPreferences(@PathVariable Long userId);

    @PutMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Updating user preferences", response = UserAndPreference.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "User preferences updated"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 404, message = "Preference not found"),
    })
    UserAndPreference updatePreferenceForUserById(@PathVariable Long userId, @RequestBody Preference preference);

    @DeleteMapping("/{userId}/{preferenceType}/{preferenceName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleting user preferences")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Preference deleted"),
            @ApiResponse(code = 404, message = "User or preference not found")
    })
    void deletePreferenceForUserById(@PathVariable Long userId, @PathVariable String preferenceType, @PathVariable String preferenceName);
}
