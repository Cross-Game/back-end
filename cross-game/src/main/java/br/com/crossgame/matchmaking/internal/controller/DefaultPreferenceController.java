package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.PreferenceController;
import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.usecase.CreatePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.DeletePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.UpdatePreferenceForUserById;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(PreferenceController.class)
public class DefaultPreferenceController implements PreferenceController {

    private CreatePreferenceForUserById createPreferenceForUserById;

    private UpdatePreferenceForUserById updatePreferenceForUserById;

    private DeletePreferenceForUserById deletePreferenceForUserById;

    @Override
    public UserAndPreference createPreferenceForUserById(Long userId, Preference preference) {
        return this.createPreferenceForUserById.execute(userId, preference);
    }

    @Override
    public UserAndPreference updatePreferenceForUserById(Long userId, Preference preference) {
        return this.updatePreferenceForUserById.execute(userId, preference);
    }

    @Override
    public void deletePreferenceForUserById(Long userId, Long preferenceId) {
        this.deletePreferenceForUserById.execute(userId, preferenceId);
    }
}
