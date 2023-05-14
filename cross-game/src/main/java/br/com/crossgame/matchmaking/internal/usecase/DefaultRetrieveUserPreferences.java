package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreferenceResponse;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserPreferences;
import br.com.crossgame.matchmaking.internal.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultRetrieveUserPreferences implements RetrieveUserPreferences {

    private RetrieveUserById retrieveUserById;

    @Override
    public UserAndPreferenceResponse execute(Long userId) {
        User user = this.retrieveUserById.execute(userId);
        return new UserAndPreferenceResponse(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isOnline(),
                user.getPreferences());
    }
}
