package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreference;
import br.com.crossgame.matchmaking.api.usecase.CreatePreferenceForUserById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultCreatePreferenceForUserById implements CreatePreferenceForUserById {

    private RetrieveUserById retrieveUserById;
    private UserRepository userRepository;

    @Override
    public UserAndPreference execute(Long userId, Preference preference) {
        User user = this.retrieveUserById.execute(userId);

        user.setPreferences(preference);
        this.userRepository.save(user);
        return new UserAndPreference(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isOnline(),
                preference);
    }
}
