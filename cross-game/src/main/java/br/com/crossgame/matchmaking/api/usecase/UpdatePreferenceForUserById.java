package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.internal.entity.Preference;

public interface UpdatePreferenceForUserById {

    UserAndPreference execute(Long userId, Preference preference);
}
