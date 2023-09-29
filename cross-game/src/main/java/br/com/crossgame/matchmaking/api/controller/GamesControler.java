package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.TypeImage;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/games-api")
public interface GamesControler {

    @GetMapping(value = {"/{gameName}/{typeImage}","/{gameName}"})
    GenericGame retrieveGameApi(@PathVariable String gameName, @PathVariable(required = false) TypeImage typeImage) throws IOException;
    @PostMapping("/{gameName}")
    Optional<GenericGame> createGame(@PathVariable String gameName) throws IOException;
}
