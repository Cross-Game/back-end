package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveGameIgdbByName;
import br.com.crossgame.matchmaking.api.usecase.RetrieveImageGame;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.ImageGame;
import br.com.crossgame.matchmaking.internal.exception.GameNotFoundException;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class DefaultRetrieveGameIgdbByName implements RetrieveGameIgdbByName {
    private final GenericGamesRepository genericGamesRepository;
    private final DefaultRetrieveGameByName retrieveGameByName;

    private RetrieveImageGame retrieveImageGame;
    @Override
    public GenericGame execute(String gameName) {
        Optional<List<GenericGame>> genericGame = genericGamesRepository.findByGameNameContainsIgnoreCase(gameName);
        if (genericGame.get().isEmpty()){
            try {
                genericGame = Optional.of(List.of(retrieveGameByName.execute(gameName,null)));

            } catch (Exception e) {
                throw new GameNotFoundException(HttpStatus.NOT_FOUND,String.format("Jogo com o nome %s não encontrado!",gameName));
            }
        }
        genericGame.ifPresent(gamesList -> {
            gamesList.forEach(game -> {
                ImageGame imageGame = retrieveImageGame.execute(game.getCoverId(), null);
                game.setImageGame(imageGame);
            });
        });
        return genericGame.stream().findFirst().get().get(0);
    }

}
