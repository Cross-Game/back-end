package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@Valid @RequestBody User user);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<User> retrieveAllUsers();

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    User retrieveUsersById(@PathVariable Long id);

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    User updateUser(@RequestBody User user);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserById(@PathVariable Long id);

    @PatchMapping(path = "/{id}/picture", consumes = "image/*")
    @ResponseStatus(HttpStatus.OK)
    void addPicture(@PathVariable Long id, @RequestBody byte[] picture);

    @GetMapping(path = "/{id}/picture", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> retrievePicture(@PathVariable Long id);

}
