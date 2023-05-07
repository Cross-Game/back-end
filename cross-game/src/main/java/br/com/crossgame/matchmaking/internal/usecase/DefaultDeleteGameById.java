package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.DeleteGameById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameById;
import br.com.crossgame.matchmaking.internal.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultDeleteGameById implements DeleteGameById {

    private RetrieveGameById retrieveGameById;

    private GameRepository gameRepository;

    @Override
    public void execute(Long gameId) {
        this.gameRepository.delete(this.retrieveGameById.execute(gameId));
    }
}
