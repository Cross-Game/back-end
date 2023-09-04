package br.com.crossgame.matchmaking.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/games-api")
public interface GamesControler {

    @GetMapping("/{gameName}")
    String retrieveGame(@PathVariable String gameName) throws IOException;
}
