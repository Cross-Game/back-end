package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.enums.TypeImage;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games-api")
@CrossOrigin(maxAge = 3600)
public interface GamesIgdbControler {

    @GetMapping("/")
    List<GenericGame> retrieveAllGames();
    @GetMapping("/internal/{gameName}")
    GenericGame retrieveGameIgdbByName(@PathVariable String gameName) throws IOException;
    @GetMapping(value = {"/{gameName}/{typeImage}","/{gameName}"})
    GenericGame retrieveGameApi(@PathVariable String gameName, @PathVariable(required = false) TypeImage typeImage) throws IOException;

    @PostMapping("/{gameName}")
    Optional<GenericGame> createGame(@PathVariable String gameName) throws IOException;
    @PutMapping("/{id}")
    GenericGame updateGameIgdb(@RequestBody GenericGame game,@PathVariable Long id);
}
