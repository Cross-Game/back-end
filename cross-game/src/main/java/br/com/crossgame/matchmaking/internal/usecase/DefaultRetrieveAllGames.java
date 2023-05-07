package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveAllGames;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.repository.GameRepository;
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
    public List<Game> execute() {
        List<Game> games = this.gameRepository.findAll();
        if (games.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return games;
    }
}
