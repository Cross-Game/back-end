package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.DeleteLinkedGameToUser;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultDeleteLinkedGameToUser implements DeleteLinkedGameToUser {

    private UserGameRepository userGameRepository;
    private RetrieveUserById retrieveUserById;

    @Override
    public void execute(Long userId, Long userGameId) {
        UserGame userGameFound = this.retrieveUserById.execute(userId)
                .getUserGames()
                .stream()
                .filter(userGame -> userGame.getId().equals(userGameId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This game id was not found"));

        this.retrieveUserById.execute(userId).getUserGames().remove(userGameFound);
        this.userGameRepository.delete(userGameFound);
    }
}
