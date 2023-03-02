package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> retrieveAllUsers();

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User retrieveUsersById(@PathVariable Long id);

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@RequestBody User user);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id);
}
