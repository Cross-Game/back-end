package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.GameUpdate;
import br.com.crossgame.matchmaking.api.usecase.UpdateGame;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdateGame implements UpdateGame {

    private GameRepository gameRepository;

    @Override
    public Game execute(GameUpdate gameUpdate) {
        return this.gameRepository.save(new Game(gameUpdate.id(),
                gameUpdate.gameName(),
                gameUpdate.gameGenre()));
    }
}
