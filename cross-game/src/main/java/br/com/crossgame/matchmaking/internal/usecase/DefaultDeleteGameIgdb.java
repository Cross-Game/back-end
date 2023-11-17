package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.internal.controller.DeleteGameIgdb;
import br.com.crossgame.matchmaking.internal.exception.GameNotFoundException;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultDeleteGameIgdb implements DeleteGameIgdb {
    private final GenericGamesRepository gamesRepository;
    @Override
    public void execute(Long id) {
      try {
          if (gamesRepository.existsById(id)){
              gamesRepository.deleteById(id);
          }
          else {
              throw new GameNotFoundException(HttpStatus.NOT_FOUND,"Game not found!");
          }
      }
      catch (Exception e){
          throw new GameNotFoundException(HttpStatus.NOT_FOUND,e.getMessage());
      }
    }
}
