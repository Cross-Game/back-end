package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.usecase.CreatePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.PreferenceDataBuldUtils;
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
    public UserAndPreference execute(Long userId, List<Preference> preferences) {
        User user = this.retrieveUserById.execute(userId);

        if (this.preferenceHasAlreadyBeenRegisteredRegistered(user, preferences)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "One of these preferences has already been registered");
        }

        if (this.containEqualRecords(preferences)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You are registering two equal preferences");
        }

        user.setPreferences(preferences);

        this.userRepository.save(user);

        return new UserAndPreference(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isOnline(),
                PreferenceDataBuldUtils.transform(preferences));
    }

    private boolean preferenceHasAlreadyBeenRegisteredRegistered(User user, List<Preference> preferences){
        boolean exists = false;
        for (Preference preference : preferences){
            exists = user.getPreferences().stream()
                    .anyMatch(p -> p.getPreferences().name().equals(preference.getPreferences().name()));
        }
        return exists;
    }

    public boolean containEqualRecords(List<Preference> preferences) {
        for (int i = 0; i < preferences.size(); i++) {
            for (int j = i + 1; j < preferences.size(); j++) {
                if (preferences.get(i).getPreferences().name().equals(
                        preferences.get(j).getPreferences().name()
                )) {
                    return true;
                }
            }
        }
        return false;
    }
}
