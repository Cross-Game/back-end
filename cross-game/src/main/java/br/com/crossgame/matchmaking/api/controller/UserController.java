package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserCompleteDataResponse;
import br.com.crossgame.matchmaking.api.model.UserCreate;
import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.model.UserDataForLoginServices;
import br.com.crossgame.matchmaking.internal.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Api(tags = "User end-points")
public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create user", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created")
    })
    User createUser(@Valid @RequestBody UserCreate userCreate);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve all users", response = ArrayList.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All users have been listed"),
            @ApiResponse(code = 204, message = "We don't have registered users yet")
    })
    List<UserData> retrieveAllUsers(User userFilter);

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve user by id", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    UserCompleteDataResponse retrieveUsersById(@PathVariable Long id);

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update user", response = User.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "User updated")
    })
    User updateUser(@RequestBody UserCreate userCreate);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 404, message = "User not found")
    })
    void deleteUserById(@PathVariable Long id);

    @PatchMapping(path = "/{id}/picture", consumes = "image/*")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add profile picture")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Saved image")
    })
    void addPicture(@PathVariable Long id, @RequestBody byte[] picture);

    @GetMapping(path = "/{id}/picture", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve profile picture")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Avatar found")
    })
    ResponseEntity<byte[]> retrievePicture(@PathVariable Long id);

    @GetMapping(path = "/validate/{username}")
    @ResponseStatus(HttpStatus.OK)
    Boolean validateByNickname(@PathVariable String username);

    @PatchMapping(path = "/update-password-by-username-email")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Retrieve user by user and email")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    void updatePasswordByUsernameEmailForLoginServices(@RequestBody UserDataForLoginServices userDataForLoginServices);
}
