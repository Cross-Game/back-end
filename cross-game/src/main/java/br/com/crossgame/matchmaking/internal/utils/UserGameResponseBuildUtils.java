package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserGameResponseBuildUtils {

    public static UserGameResponse transform(UserGame userGame){
        return new UserGameResponse(userGame.getId(),
                userGame.isFavoriteGame(),
                userGame.getUserNickname(),
                userGame.getGamerId(),
                userGame.getSkillLevel(),
                userGame.getGameFunction(),
                userGame.getUser().getId(),
                userGame.getGame().getId());
    }
}
