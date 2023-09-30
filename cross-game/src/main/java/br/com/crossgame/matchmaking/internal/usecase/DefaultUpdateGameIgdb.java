package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.UpdateGameIgdb;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DefaultUpdateGameIgdb implements UpdateGameIgdb {
    private final GenericGamesRepository genericGamesRepository;
    @Override
    public GenericGame execute(GenericGame updatedGame,Long id) {
        if (genericGamesRepository.existsById(id)){
            GenericGame genericGame = genericGamesRepository.findById(id).orElseThrow();
            genericGame.setGameName(updatedGame.getGameName());
            genericGame.setCoverId(updatedGame.getCoverId());
            genericGame.setImageGame(updatedGame.getImageGame());
           return genericGamesRepository.save(genericGame);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Game not found!");
    }
}
