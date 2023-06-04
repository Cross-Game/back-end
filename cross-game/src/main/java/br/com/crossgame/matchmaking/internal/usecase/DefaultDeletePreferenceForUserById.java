package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.DeletePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import br.com.crossgame.matchmaking.internal.repository.PreferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DefaultDeletePreferenceForUserById implements DeletePreferenceForUserById {

    private final PreferenceRepository preferenceRepository;
    private final RetrieveUserById retrieveUserById;

    @Override
    public void execute(Long userId, String preferenceType, String preferenceName) {
        User user = retrieveUserById.execute(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        boolean preferenceFound = false;

        switch (preferenceType) {
            case "food":
                var foodPreferences = user.getPreferences().getFood();
                if (foodPreferences.contains(FoodType.valueOf(preferenceName))) {
                    foodPreferences.remove(FoodType.valueOf(preferenceName));
                    preferenceFound = true;
                }
                break;
            case "movieGenre":
                var movieGenrePreferences = user.getPreferences().getMovieGenre();
                if (movieGenrePreferences.contains(MovieGenre.valueOf(preferenceName))) {
                    movieGenrePreferences.remove(MovieGenre.valueOf(preferenceName));
                    preferenceFound = true;
                }
                break;
            case "seriesGenre":
                var seriesGenrePreferences = user.getPreferences().getSeriesGenre();
                if (seriesGenrePreferences.contains(SeriesGenre.valueOf(preferenceName))) {
                    seriesGenrePreferences.remove(SeriesGenre.valueOf(preferenceName));
                    preferenceFound = true;
                }
                break;
            case "gameGenre":
                var gameGenrePreferences = user.getPreferences().getGameGenre();
                if (gameGenrePreferences.contains(GameGenre.valueOf(preferenceName))) {
                    gameGenrePreferences.remove(GameGenre.valueOf(preferenceName));
                    preferenceFound = true;
                }
                break;
            case "musicGenre":
                var musicGenrePreferences = user.getPreferences().getMusicGenre();
                if (musicGenrePreferences.contains(MusicGenre.valueOf(preferenceName))) {
                    musicGenrePreferences.remove(MusicGenre.valueOf(preferenceName));
                    preferenceFound = true;
                }
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid preference type");
        }

        if (!preferenceFound) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Preference not found");
        }

        preferenceRepository.save(user.getPreferences());
    }
}