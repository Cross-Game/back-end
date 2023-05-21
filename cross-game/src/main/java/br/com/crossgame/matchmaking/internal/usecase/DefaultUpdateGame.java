package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.GameResponse;
import br.com.crossgame.matchmaking.api.model.GameUpdate;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameById;
import br.com.crossgame.matchmaking.api.usecase.UpdateGame;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.repository.GameRepository;
import br.com.crossgame.matchmaking.internal.utils.GameResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdateGame implements UpdateGame {

    private GameRepository gameRepository;

    private RetrieveGameById retrieveGameById;

    @Override
    public GameResponse execute(GameUpdate gameUpdate) {
        Game game = this.retrieveGameById.execute(gameUpdate.id());
        game.setGameName(gameUpdate.gameName());
        game.setGameGenre(gameUpdate.gameGenre());
        this.gameRepository.save(game);
        return GameResponseBuildUtils.transform(game);
    }
}
