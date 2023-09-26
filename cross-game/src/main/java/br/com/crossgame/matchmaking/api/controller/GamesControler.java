package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/games-api")
public interface GamesControler {

    @GetMapping("/{gameName}")
    GenericGame retrieveGameApi(@PathVariable String gameName) throws IOException;
    @PostMapping("/{gameName}")
    Optional<GenericGame> createGame(@PathVariable String gameName) throws IOException;
}
