package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.usecase.RetrieveLinkedGamesByGameName;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultRetrieveLinkedGamesByGameName implements RetrieveLinkedGamesByGameName {
    private RetrieveUserById retrieveUserById;

    @Override
    public Optional<List<GenericGame>> execute(Long userId, String gameName) {
        List<UserGame> userGames = this.retrieveUserById.execute(userId)
                .getUserGames();
        if (userGames.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "This user does not have registered games yet");
        }
        List<GenericGame> filteredGames = userGames.stream()
                .map(UserGame::getGenericGames)
                .flatMap(List::stream)
                .filter(game -> game.getGameName().contains(gameName))
                .collect(Collectors.toList());
        return Optional.of(filteredGames);
    }


}
