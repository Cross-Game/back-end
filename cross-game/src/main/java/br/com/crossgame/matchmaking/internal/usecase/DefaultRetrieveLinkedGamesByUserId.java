package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveLinkedGamesByUserId;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveLinkedGamesByUserId implements RetrieveLinkedGamesByUserId {

    private RetrieveUserById retrieveUserById;

    @Override
    public List<UserGame> execute(Long userId) {
        List<UserGame> userGames = this.retrieveUserById.execute(userId)
                .getUserGames();
        if (userGames.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "This user does not have registered games yet");
        }
        return userGames;
    }
}
