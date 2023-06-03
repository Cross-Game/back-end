package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.api.model.GameData;
import br.com.crossgame.matchmaking.api.model.UserCompleteDataResponse;
import br.com.crossgame.matchmaking.api.model.UserGameData;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserCompleteDataResponseBuildUtils {

    public static UserCompleteDataResponse transform(User user){
        return new UserCompleteDataResponse(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isOnline(),
                user.getFriends(),
                user.getPreferences(),
                user.getFeedbacks(),
                user.getPlatforms(),
                convertUserGamesToUserGameData(user.getUserGames()));
    }

    private List<UserGameData> convertUserGamesToUserGameData(List<UserGame> userGames){
        List<UserGameData> userGameDataList = new ArrayList<>();
        for (UserGame userGame : userGames){
            userGameDataList.add(new UserGameData(userGame.getId(),
                    userGame.isFavoriteGame(),
                    userGame.getUserNickname(),
                    userGame.getGamerId(),
                    userGame.getSkillLevel(),
                    convertGameToGameData(userGame.getGame())));
        }
        return userGameDataList;
    }

    private GameData convertGameToGameData(Game game){
        return new GameData(game.getId(),
                game.getGameName(),
                game.getGameGenre(),
                PlataformDataBuildUtils.transform(game.getPlataforms()));
    }
}
