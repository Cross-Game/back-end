package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;

import java.util.List;

public record UserCompleteDataResponse(Long id,
                                       String username,
                                       String email,
                                       Role role,
                                       Boolean isOnline,
                                       List<Friend> friends,
                                       List<PreferenceData> preferencesData,
                                       List<Feedback> feedbacks,
                                       List<UserGameData> userGameData) {
}
