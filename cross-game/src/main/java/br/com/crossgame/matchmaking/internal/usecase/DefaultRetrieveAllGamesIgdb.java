package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveAllGamesIgdb;
import br.com.crossgame.matchmaking.api.usecase.RetrieveImageGame;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllGamesIgdb implements RetrieveAllGamesIgdb {

    private GenericGamesRepository genericGamesRepository;
    private RetrieveImageGame retrieveImageGame;

    @Override
    public List<GenericGame> execute() {
        List<GenericGame> all = genericGamesRepository.findAll();
        all.forEach(game -> game.setImageGame(retrieveImageGame.execute(game.getCoverId(), null)));
        return all;
    }
}
