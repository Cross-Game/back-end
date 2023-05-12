package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.usecase.CreatePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
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

        if (this.preferenceHasAlreadyBeenRegisteredRegistered(user.getPreferences(), preference)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "One of these preferences has already been registered");
        }

        user.setPreferences(preference);
        this.userRepository.save(user);

        return new UserAndPreference(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isOnline(),
                preference);
    }

    private boolean preferenceHasAlreadyBeenRegisteredRegistered(List<Preference> userPreferences,
                                                                 Preference preference){
        return (userPreferences.stream().anyMatch(pref -> pref.getFood().equals(preference.getFood()) ||
                userPreferences.stream().anyMatch(pref2 -> pref2.getGameGenre().equals(preference.getGameGenre()) ||
                userPreferences.stream().anyMatch(pref3 -> pref3.getMovieGenre().equals(preference.getMovieGenre()) ||
                userPreferences.stream().anyMatch(pref4 -> pref4.getSeriesGenre().equals(preference.getSeriesGenre()))))));
    }
}
