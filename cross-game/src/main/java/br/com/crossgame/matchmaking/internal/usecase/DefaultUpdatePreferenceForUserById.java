package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.api.usecase.UpdatePreferenceForUserById;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.FoodType;
import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;
import br.com.crossgame.matchmaking.internal.entity.enums.MovieGenre;
import br.com.crossgame.matchmaking.internal.entity.enums.SeriesGenre;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdatePreferenceForUserById implements UpdatePreferenceForUserById {

    private RetrieveUserById retrieveUserById;

    private UserRepository userRepository;

    @Override
    public UserAndPreference execute(Long userId, Preference preference) {
        User user = this.retrieveUserById.execute(userId);
        Preference userPreference = user.getPreferences();

        if (userPreference == null) {
            userPreference = new Preference();
        }

        List<FoodType> newFood = preference.getFood();
        List<MovieGenre> newMovieGenre = preference.getMovieGenre();
        List<SeriesGenre> newSeriesGenre = preference.getSeriesGenre();
        List<GameGenre> newGameGenre = preference.getGameGenre();

        for (FoodType food : newFood) {
            if (!userPreference.getFood().contains(food)) {
                userPreference.getFood().add(food);
            }
        }
        for (MovieGenre movieGenre : newMovieGenre) {
            if (!userPreference.getMovieGenre().contains(movieGenre)) {
                userPreference.getMovieGenre().add(movieGenre);
            }
        }
        for (SeriesGenre seriesGenre : newSeriesGenre) {
            if (!userPreference.getSeriesGenre().contains(seriesGenre)) {
                userPreference.getSeriesGenre().add(seriesGenre);
            }
        }
        for (GameGenre gameGenre : newGameGenre) {
            if (!userPreference.getGameGenre().contains(gameGenre)) {
                userPreference.getGameGenre().add(gameGenre);
            }
        }

        this.userRepository.save(user);

        return new UserAndPreference(user.getId(), user.getUsername(), user.getEmail(), user.getRole(),
                user.isOnline(), userPreference);
    }
}