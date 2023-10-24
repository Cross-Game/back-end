package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveGameIgdbByName;
import br.com.crossgame.matchmaking.api.usecase.RetrieveImageGame;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.ImageGame;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class DefaultRetrieveGameIgdbByName implements RetrieveGameIgdbByName {
    private final GenericGamesRepository genericGamesRepository;

    private RetrieveImageGame retrieveImageGame;
    @Override
    public Optional<List<GenericGame>> execute(String gameName) {
        Optional<List<GenericGame>> genericGame = genericGamesRepository.findByGameNameContainsIgnoreCase(gameName);
        if (genericGame.isEmpty()){
            return Optional.empty();
        }
        genericGame.ifPresent(gamesList -> {
            gamesList.forEach(game -> {
                ImageGame imageGame = retrieveImageGame.execute(game.getCoverId(), null);
                game.setImageGame(imageGame);
            });
        });
        return genericGame;
    }

}
