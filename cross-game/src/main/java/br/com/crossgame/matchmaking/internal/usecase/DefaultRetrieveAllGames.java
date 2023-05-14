package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.GameResponse;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllGames;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.repository.GameRepository;
import br.com.crossgame.matchmaking.internal.utils.GameResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllGames implements RetrieveAllGames {

    private GameRepository gameRepository;

    @Override
    public List<GameResponse> execute() {
        List<Game> games = this.gameRepository.findAll();
        if (games.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return GameResponseBuildUtils.transform(games);
    }
}
