package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreferenceResponse;

public interface RetrieveUserPreferences {

    UserAndPreferenceResponse execute(Long userId);
}
