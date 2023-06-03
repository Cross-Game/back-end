package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndPreferenceResponse;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.api.usecase.UpdateGamePlatformsForUserById;
import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;
import br.com.crossgame.matchmaking.internal.entity.Plataform;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import br.com.crossgame.matchmaking.internal.repository.PlataformRepository;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import br.com.crossgame.matchmaking.internal.repository.UserGameplayPlatformRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.UserGameResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdateGamePlatformsForUserById implements UpdateGamePlatformsForUserById {
    private RetrieveUserById retrieveUserById;
    private UserRepository userRepository;

    private UserGameplayPlatformRepository gameplayPlatformRepository;

    @Override
    public List<GameplayPlatformType> execute(Long userId, List<GameplayPlatformType> gameplayPlatformTypes) {
        User user = this.retrieveUserById.execute(userId);


        List<GameplayPlatformType> userPlatforms = new ArrayList<>();
        List<GameplayPlatform> gameplayPlatforms = new ArrayList<>();

        gameplayPlatformTypes.forEach(gameplayPlatformType -> {
            GameplayPlatform gameplayPlatform = new GameplayPlatform();
            gameplayPlatform.setUser(user);
            gameplayPlatform.setGameplayPlataformType(gameplayPlatformType);
            gameplayPlatforms.add(gameplayPlatformRepository.save(gameplayPlatform));
            userPlatforms.add(gameplayPlatformType);
            user.getPlatforms().add(gameplayPlatform);
        });

        userRepository.save(user);
        return userPlatforms;
    }
}
