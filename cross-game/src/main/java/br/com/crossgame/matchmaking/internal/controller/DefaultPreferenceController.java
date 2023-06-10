package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.PreferenceController;
import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.model.UserAndPreferenceResponse;
import br.com.crossgame.matchmaking.api.usecase.CreatePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.DeletePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserPreferences;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(PreferenceController.class)
public class DefaultPreferenceController implements PreferenceController {

    private CreatePreferenceForUserById createPreferenceForUserById;

    private DeletePreferenceForUserById deletePreferenceForUserById;

    private RetrieveUserPreferences retrieveUserPreferences;

    @Override
    public UserAndPreference createPreferenceForUserById(Long userId, List<Preference> preferences) {
        return this.createPreferenceForUserById.execute(userId, preferences);
    }

    @Override
    public UserAndPreferenceResponse retrieveUserPreferences(Long userId) {
        return this.retrieveUserPreferences.execute(userId);
    }

    @Override
    public void deletePreferenceForUserById(Long userId, Long preferenceId) {
        this.deletePreferenceForUserById.execute(userId, preferenceId);
    }
}
