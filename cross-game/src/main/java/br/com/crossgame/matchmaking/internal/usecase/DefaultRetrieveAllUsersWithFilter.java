package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllUsersWithFilter;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllUsersWithFilter implements RetrieveAllUsersWithFilter {
    @Override
    public List<UserData> execute(SkillLevel skillLevel,
                                  GameFunction gameFunction,
                                  String gameName,
                                  GameGenre gameGenre,
                                  FoodType foodType,
                                  MovieGenre movieGenre,
                                  SeriesGenre seriesGenre,
                                  GameGenre gameGenrePreference) {
        return null;
    }
}
