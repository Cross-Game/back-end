package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveLinkedGamePlatformsByUserId;
import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DefaultRetrieveLinkedGamePlatformsByUserId implements RetrieveLinkedGamePlatformsByUserId {
    @Override
    public List<GameplayPlatform> execute(Long userId) {
        return null;
    }
}
