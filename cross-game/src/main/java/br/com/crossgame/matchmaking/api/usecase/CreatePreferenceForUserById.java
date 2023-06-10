package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.internal.entity.Preference;

import java.util.List;

public interface CreatePreferenceForUserById {

    UserAndPreference execute(Long userId, List<Preference> preference);
}
