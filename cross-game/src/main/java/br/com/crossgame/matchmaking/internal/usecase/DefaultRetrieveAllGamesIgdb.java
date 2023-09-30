package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveAllGamesIgdb;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DefaultRetrieveAllGamesIgdb implements RetrieveAllGamesIgdb {

    private  GenericGamesRepository genericGamesRepository;

    @Override
    public List<GenericGame> execute() {
        return genericGamesRepository.findAll();
    }
}
