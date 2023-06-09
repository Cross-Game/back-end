package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllUsersWithFilter;
import br.com.crossgame.matchmaking.internal.entity.*;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import br.com.crossgame.matchmaking.internal.repository.CustomUserFilterRepository;
import br.com.crossgame.matchmaking.internal.utils.FilaObj;
import br.com.crossgame.matchmaking.internal.utils.QueryBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllUsersWithFilter implements RetrieveAllUsersWithFilter {

    private CustomUserFilterRepository customUserFilterRepository;

    @Override
    public FilaObj<UserData> execute(SkillLevel skillLevel,
                                  GameFunction gameFunction,
                                  String gameName,
                                  GameGenre gameGenre,
                                  Preferences preferences,
                                  Preferences preferences2,
                                  Preferences preferences3,
                                  boolean skillLevelFeedback,
                                  boolean behaviorFeedback) {

        if (this.verifyPreferenceParamEquality(preferences, preferences2, preferences3)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot have two equal preference parameters");
        }

        QueryBuilder.clearList();
        QueryBuilder.setUserGames(new UserGame(null, false, null, null, skillLevel, gameFunction));
        QueryBuilder.setGames(new Game(null, gameName, gameGenre));
        QueryBuilder.setPreferences(new Preference(preferences));
        QueryBuilder.setPreferences(new Preference(preferences2));
        QueryBuilder.setPreferences(new Preference(preferences3));

        if (skillLevelFeedback){
            return this.convertToFilaObj(
                    this.sortByAvgSkillLevel(this.customUserFilterRepository.findAllUsersByFilter())
            );
        } else if (behaviorFeedback){
            return this.convertToFilaObj(
                    this.sortByAvgBehaviorLevel(this.customUserFilterRepository.findAllUsersByFilter())
            );
        } else if (skillLevelFeedback && behaviorFeedback){
            return this.convertToFilaObj(
                    this.sortByAvgBehaviorAndSkillLevel(this.customUserFilterRepository.findAllUsersByFilter())
            );
        } else {
            return this.convertToFilaObj(
                    this.convertUserToUserData(this.customUserFilterRepository.findAllUsersByFilter())
            );
        }
    }

    private boolean verifyPreferenceParamEquality(Preferences preferences, Preferences preferences2, Preferences preferences3){
        if (Objects.isNull(preferences) && Objects.isNull(preferences2) && Objects.isNull(preferences3)){
            return false;
        } else if (!Objects.isNull(preferences) && !Objects.isNull(preferences2) && !Objects.isNull(preferences3)) {
            return preferences.name().equals(preferences2.name()) ||
                    preferences.name().equals(preferences3.name()) ||
                    preferences2.name().equals(preferences3.name());
        } else if (!Objects.isNull(preferences) && !Objects.isNull(preferences2) && Objects.isNull(preferences3)){
            return preferences.name().equals(preferences2.name());
        } else if (!Objects.isNull(preferences) && Objects.isNull(preferences2) && !Objects.isNull(preferences3)) {
            return preferences.name().equals(preferences3.name());
        } else if (Objects.isNull(preferences) && !Objects.isNull(preferences2) && !Objects.isNull(preferences3)) {
            return preferences2.name().equals(preferences3.name());
        }
            return false;
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

    private FilaObj<UserData> convertToFilaObj(List<UserData> userDataList){
        FilaObj<UserData> filaObj = new FilaObj<>(userDataList.size());
        for (UserData userData : userDataList){
            filaObj.insert(userData);
        }
        return filaObj;
    }
}
