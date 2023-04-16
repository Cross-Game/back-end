package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Controladora de Usuario", description = "Realiza operações com o usuario")
@RestController
@RequestMapping(path = "/users")
public interface UserController {

    @ApiOperation(value = "Realiza a criação de um usuario", response = String.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user);

    @ApiOperation(value = "Realiza a busca de todos os usuarios", response = String.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> retrieveAllUsers();

    @ApiOperation(value = "Realiza a busca de um usuario pelo identificador", response = String.class)
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User retrieveUsersById(@PathVariable Long id);

    @ApiOperation(value = "Realiza a Atualização de um usuario", response = String.class)
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@RequestBody User user);

    @ApiOperation(value = "Remove o usuario do banco de dados", response = String.class)
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id);
}
