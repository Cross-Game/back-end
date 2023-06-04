package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.usecase.CreatePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
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
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DefaultCreatePreferenceForUserById implements CreatePreferenceForUserById {

    private RetrieveUserById retrieveUserById;
    private UserRepository userRepository;

    @Override
    public UserAndPreference execute(Long userId, Preference preference) {
        User user = this.retrieveUserById.execute(userId);
        Preference userPreference = user.getPreferences();

        if (userPreference == null) {
            userPreference = new Preference();
            userPreference.setFood(new ArrayList<>());
            userPreference.setMovieGenre(new ArrayList<>());
            userPreference.setSeriesGenre(new ArrayList<>());
            userPreference.setGameGenre(new ArrayList<>());
        }

        List<FoodType> newFood = preference.getFood();
        List<MovieGenre> newMovieGenre = preference.getMovieGenre();
        List<SeriesGenre> newSeriesGenre = preference.getSeriesGenre();
        List<GameGenre> newGameGenre = preference.getGameGenre();

        // Adicionar preferências que não estão em conflito
        addNonConflictingPreferences(userPreference.getFood(), newFood);
        addNonConflictingPreferences(userPreference.getMovieGenre(), newMovieGenre);
        addNonConflictingPreferences(userPreference.getSeriesGenre(), newSeriesGenre);
        addNonConflictingPreferences(userPreference.getGameGenre(), newGameGenre);

        user.setPreferences(userPreference);
        this.userRepository.save(user);

        return new UserAndPreference(user.getId(), user.getUsername(), user.getEmail(), user.getRole(),
                user.isOnline(), userPreference);
    }

    private <E extends Enum<E>> void addNonConflictingPreferences(List<E> existingPreferences, List<E> newPreferences) {
        for (E preference : newPreferences) {
            if (!existingPreferences.contains(preference)) {
                existingPreferences.add(preference);
            }
        }
    }
}






