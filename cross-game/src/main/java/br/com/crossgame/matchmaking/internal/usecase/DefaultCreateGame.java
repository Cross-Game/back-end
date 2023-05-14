package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.GameData;
import br.com.crossgame.matchmaking.api.model.GameResponse;
import br.com.crossgame.matchmaking.api.usecase.CreateGame;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.repository.GameRepository;
import br.com.crossgame.matchmaking.internal.utils.GameResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultCreateGame implements CreateGame {

    private GameRepository gameRepository;

    @Override
    public GameResponse execute(GameData gameData) {
        Game game = new Game(null,
                gameData.gameName(),
                gameData.gameGenre());
        if(this.thereIsThisGameRegistered(game)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This game already exists");
        }
        game.setGameName(game.getGameName().toUpperCase());
        gameData.plataforms().forEach(game::setPlataforms);
        this.gameRepository.save(game);
        return GameResponseBuildUtils.transform(game);
    }

    private boolean thereIsThisGameRegistered(Game game){
        return this.gameRepository.findAll().stream()
                .anyMatch(gameExists -> gameExists.getGameName().equalsIgnoreCase(game.getGameName()));
    }
}