package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllUsersWithFilter;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import br.com.crossgame.matchmaking.internal.repository.CustomUserFilterRepository;
import br.com.crossgame.matchmaking.internal.utils.QueryBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllUsersWithFilter implements RetrieveAllUsersWithFilter {

    private CustomUserFilterRepository customUserFilterRepository;

    @Override
    public List<UserData> execute(SkillLevel skillLevel,
                                  GameFunction gameFunction,
                                  String gameName,
                                  GameGenre gameGenre,
                                  FoodType foodType,
                                  MovieGenre movieGenre,
                                  SeriesGenre seriesGenre,
                                  GameGenre gameGenrePreference) {
        QueryBuilder.clearList();
        QueryBuilder.setUserGames(new UserGame(null, false, null, null, skillLevel, gameFunction));
        QueryBuilder.setGames(new Game(null, gameName, gameGenre));
        QueryBuilder.setPreferences(new Preference(foodType, movieGenre, seriesGenre, gameGenrePreference));

        return this.convertUserToUserData(customUserFilterRepository.findAllUsersByFilter());
    }

    private List<UserData> convertUserToUserData(List<User> users){
        List<UserData> userData = new ArrayList<>();
        for (User user : users){
            userData.add(new UserData(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole(),
                    user.isOnline()));
        }
        return userData;
    }
}
