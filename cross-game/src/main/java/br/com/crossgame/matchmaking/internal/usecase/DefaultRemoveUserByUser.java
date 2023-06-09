package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RemoveUserByUser;
import org.springframework.stereotype.Service;

@Service
public class DefaultRemoveUserByUser implements RemoveUserByUser {
    @Override
    public void execute(Long userId, Long roomId) {

    }
}
