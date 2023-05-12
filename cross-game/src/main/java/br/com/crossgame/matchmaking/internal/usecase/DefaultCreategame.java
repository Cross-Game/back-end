package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.CreateGame;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultCreategame implements CreateGame {

    private GameRepository gameRepository;

    @Override
    public Game execute(Game game) {
        if(this.thereIsThisGameRegistered(game)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This game already exists");
        }
        game.setGameName(game.getGameName().toUpperCase());
        return this.gameRepository.save(game);
    }

    private boolean thereIsThisGameRegistered(Game game){
        return this.gameRepository.findAll().stream()
                .anyMatch(gameExists -> gameExists.getGameName().equalsIgnoreCase(game.getGameName()));
    }
}
