package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserFilterController;
import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllUsersWithFilter;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(UserFilterController.class)
public class DefaultUserFilterController implements UserFilterController{

    private RetrieveAllUsersWithFilter retrieveAllUsersWithFilter;

    @Override
    public List<UserData> retrieveAllUsersWithFilter(SkillLevel skillLevel,
                                                     GameFunction gameFunction,
                                                     String gameName,
                                                     GameGenre gameGenre,
                                                     FoodType foodType,
                                                     MovieGenre movieGenre,
                                                     SeriesGenre seriesGenre,
                                                     GameGenre gameGenrePreference) {
        return this.retrieveAllUsersWithFilter.execute(skillLevel,
                gameFunction,
                gameName,
                gameGenre,
                foodType,
                movieGenre,
                seriesGenre,
                gameGenrePreference);
    }
}
