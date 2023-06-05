package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllUsersWithFilter;
import br.com.crossgame.matchmaking.internal.entity.*;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import br.com.crossgame.matchmaking.internal.repository.CustomUserFilterRepository;
import br.com.crossgame.matchmaking.internal.utils.QueryBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
                                  GameGenre gameGenrePreference,
                                  boolean skillLevelFeedback,
                                  boolean behaviorFeedback) {
        QueryBuilder.clearList();
        QueryBuilder.setUserGames(new UserGame(null, false, null, null, skillLevel, gameFunction));
        QueryBuilder.setGames(new Game(null, gameName, gameGenre));
        //QueryBuilder.setPreferences(new Preference(foodType, movieGenre, seriesGenre, gameGenrePreference));

        if (skillLevelFeedback){
            return this.sortByAvgSkillLevel(this.customUserFilterRepository.findAllUsersByFilter());
        } else if (behaviorFeedback){
            return this.sortByAvgBehaviorLevel(this.customUserFilterRepository.findAllUsersByFilter());
        } else if (skillLevelFeedback && behaviorFeedback){
            return this.sortByAvgBehaviorAndSkillLevel(this.customUserFilterRepository.findAllUsersByFilter());
        } else {
            return this.convertUserToUserData(this.customUserFilterRepository.findAllUsersByFilter());
        }
    }

    private List<UserData> sortByAvgSkillLevel(List<User> userList){
        return this.convertUserToUserData(userList
                .stream()
                .sorted(Comparator.comparingDouble(users -> users.getFeedbacks().stream()
                        .mapToInt(Feedback::getBehavior)
                        .average()
                        .orElse(0.0)))
                .toList());
    }

    private List<UserData> sortByAvgBehaviorLevel(List<User> userList){
        return this.convertUserToUserData(userList
                .stream()
                .sorted(Comparator.comparingDouble(users -> users.getFeedbacks().stream()
                        .mapToInt(Feedback::getSkill)
                        .average()
                        .orElse(0.0)))
                .toList());
    }

    private List<UserData> sortByAvgBehaviorAndSkillLevel(List<User> userList){
        return this.convertUserToUserData(userList
                .stream()
                .sorted(Comparator.comparingDouble((User users) -> users.getFeedbacks().stream()
                        .mapToInt(Feedback::getSkill)
                        .average()
                        .orElse(0.0))
                        .thenComparingDouble(users -> users.getFeedbacks().stream()
                        .mapToInt(Feedback::getBehavior)
                        .average()
                        .orElse(0.0)))
                .toList());
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
