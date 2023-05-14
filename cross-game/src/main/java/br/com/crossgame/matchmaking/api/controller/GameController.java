package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.GameData;
import br.com.crossgame.matchmaking.api.model.GameResponse;
import br.com.crossgame.matchmaking.api.model.GameUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/games")
@Api(tags = "Game end-points")
public interface GameController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Game created")
    })
    GameResponse createGame(@RequestBody GameData gameData);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve all games", response = ArrayList.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All games have been listed"),
            @ApiResponse(code = 204, message = "We don't have registered any game yet")
    })
    List<GameResponse> retrieveAllGames();

    @GetMapping(path = "/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieve game by id", response = ArrayList.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Game found"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    GameResponse retrieveGameById(@PathVariable Long gameId);

    @DeleteMapping(path = "/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete game")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Game deleted"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    void deleteGameById(@PathVariable Long gameId);

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update game")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Game updated")
    })
    GameResponse updateGame(@RequestBody GameUpdate gameUpdate);
}
