package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.internal.entity.enums.*;

import java.util.List;

public interface RetrieveAllUsersWithFilter {

    List<UserData> execute(SkillLevel skillLevel,
                          GameFunction gameFunction,
                          String gameName,
                          GameGenre gameGenre,
                          FoodType foodType,
                          MovieGenre movieGenre,
                          SeriesGenre seriesGenre,
                          GameGenre gameGenrePreference,
                           MusicGenre musicGenre,
                           boolean skillLevelFeedback,
                           boolean behaviorFeedback);
}
