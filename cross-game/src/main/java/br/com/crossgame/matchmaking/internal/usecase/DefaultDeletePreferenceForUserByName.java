package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.DeletePreferenceForUserByName;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.PreferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultDeletePreferenceForUserByName  implements DeletePreferenceForUserByName {
    private PreferenceRepository preferenceRepository;
    private RetrieveUserById retrieveUserById;

    @Override
    public void execute(Long userId, String name) {
        User user = this.retrieveUserById.execute(userId);
        Preference preferenceToRemove = user.getPreferences()
                .stream()
                .filter(preference -> preference.equals(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Preference to delete not found"));
        user.getPreferences().remove(preferenceToRemove);

        this.preferenceRepository.delete(preferenceToRemove);
    }
}
