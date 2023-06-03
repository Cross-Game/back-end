package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreferenceResponse;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.api.usecase.UpdateGamePlatformsForUserById;
import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import br.com.crossgame.matchmaking.internal.repository.UserGameplayPlatformRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.UserGameResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdateGamePlatformsForUserById implements UpdateGamePlatformsForUserById {
    private RetrieveUserById retrieveUserById;
    private UserRepository userRepository;

    @Override
    public List<GameplayPlatformType> execute(Long userId, List<GameplayPlatformType> gameplayPlatformTypes) {
        User user = this.retrieveUserById.execute(userId);
        List<GameplayPlatformType> userPlatforms = user.getPlatforms().stream()
                .map(GameplayPlatform::getGameplayPlataformType)
                .collect(Collectors.toList());

        for (GameplayPlatformType platform : gameplayPlatformTypes) {
            if (!userPlatforms.contains(platform)) {
                userPlatforms.add(platform);
            }
        }

        userRepository.save(user);
        return userPlatforms;
    }
}
