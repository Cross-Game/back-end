package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/preferences")
@Api(tags = "Preference end-points")
public interface PreferenceController {

    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user preferences", response = UserAndPreference.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "User preferences created")
    })
    UserAndPreference createPreferenceForUserById(@PathVariable Long userId, @RequestBody Preference preference);

    @PutMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Updating user preferences", response = UserAndPreference.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "User preferences updated"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 404, message = "Preference not found"),
    })
    UserAndPreference updatePreferenceForUserById(@PathVariable Long userId, @RequestBody Preference preference);

    @DeleteMapping(path = "/{userId}/{preferenceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleting user preferences")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User preferences updated"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 404, message = "Preference not found"),
    })
    void deletePreferenceForUserById(Long userId, Long preferenceId);
}
