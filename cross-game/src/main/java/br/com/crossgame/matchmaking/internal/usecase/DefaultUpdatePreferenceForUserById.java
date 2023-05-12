package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.api.usecase.UpdatePreferenceForUserById;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.PreferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DefaultUpdatePreferenceForUserById implements UpdatePreferenceForUserById {

    private RetrieveUserById retrieveUserById;

    private PreferenceRepository preferenceRepository;

    @Override
    public UserAndPreference execute(Long userId, Preference preference) {
        User user = this.retrieveUserById.execute(userId);
        Preference preferenceFound = user.getPreferences().stream()
                .filter(pref -> pref.getId().equals(preference.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Preference to update not found"));

        user.getPreferences().set(user.getPreferences().indexOf(preferenceFound), preference);

        this.preferenceRepository.save(preference);
        return new UserAndPreference(user.getId(), user.getUsername(), user.getEmail(), user.getRole(),
                user.isOnline(), preference);
    }
}
