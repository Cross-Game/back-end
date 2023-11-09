package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.GameData;
import br.com.crossgame.matchmaking.api.model.GameResponse;
import br.com.crossgame.matchmaking.api.model.PlataformData;
import br.com.crossgame.matchmaking.api.usecase.CreateGame;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.Plataform;
import br.com.crossgame.matchmaking.internal.repository.GameRepository;
import br.com.crossgame.matchmaking.internal.repository.PlataformRepository;
import br.com.crossgame.matchmaking.internal.utils.GameResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DefaultCreateGame implements CreateGame {

    private GameRepository gameRepository;

    private PlataformRepository plataformRepository;

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
        this.setRegisteredPlataformForGame(game, gameData.plataforms());
        this.gameRepository.save(game);
        return GameResponseBuildUtils.transform(game);
    }

    private boolean thereIsThisGameRegistered(Game game){
        return this.gameRepository.findAll().stream()
                .anyMatch(gameExists -> gameExists.getGameName().equalsIgnoreCase(game.getGameName()));
    }

    private void setRegisteredPlataformForGame(Game game, List<PlataformData> plataformsData){
        for (PlataformData plataformData : plataformsData){
            Plataform plataform = this.plataformRepository.findByPlataformType(plataformData.plataformType())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Plataform %s is not registered", plataformData.plataformType().name()
                            )));

            game.setPlataforms(plataform);
        }
    }
}