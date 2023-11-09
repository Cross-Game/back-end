package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.usecase.RetrieveLinkedGamesByUserId;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveLinkedGamesByUserId implements RetrieveLinkedGamesByUserId {

    private RetrieveUserById retrieveUserById;

    @Override
    public List<UserGameResponse> execute(Long userId) {
        List<UserGame> userGames = this.retrieveUserById.execute(userId)
                .getUserGames();
        if (userGames.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "This user does not have registered games yet");
        }
        return this.convertToUserGameResponseList(userGames);
    }

    private List<UserGameResponse> convertToUserGameResponseList(List<UserGame> userGames){
        List<UserGameResponse> userGameResponses = new ArrayList<>();
        for (UserGame userGame : userGames){
            userGameResponses.add(new UserGameResponse(userGame.getId(),
                    userGame.isFavoriteGame(),
                    userGame.getUserNickname(),
                    userGame.getGamerId(),
                    userGame.getSkillLevel(),
                    userGame.getGameFunction(),
                    userGame.getUser().getId(),
                    userGame.getGenericGames()));
        }
        return userGameResponses;
    }
}
