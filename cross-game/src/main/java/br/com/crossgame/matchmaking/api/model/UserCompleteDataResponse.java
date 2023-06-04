package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;

import java.util.List;

public record UserCompleteDataResponse(Long id,
                                       String username,
                                       String email,
                                       Role role,
                                       Boolean isOnline,
                                       List<Friend> friends,
                                       Preference preferences,
                                       List<Feedback> feedbacks,
                                       List<GameplayPlatformData> platforms,
                                       List<UserGameData> userGameData) {
}
