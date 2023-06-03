package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveLinkedGamePlatformsByUserId;
import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DefaultRetrieveLinkedGamePlatformsByUserId implements RetrieveLinkedGamePlatformsByUserId {
    private UserRepository userRepo;
    @Override
    public List<GameplayPlatformType> execute(Long userId) {
        var user = userRepo.findById(userId);
        var platformsReturn = new ArrayList<GameplayPlatformType>();
        var platforms = user.get().getPlatforms();
        for (var platfoms : platforms){
            platformsReturn.add(platfoms.getGameplayPlataformType());
        }
        return platformsReturn;
    }
}
