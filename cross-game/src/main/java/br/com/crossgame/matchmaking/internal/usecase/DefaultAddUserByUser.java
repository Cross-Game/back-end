package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.AddUserByUser;
import org.springframework.stereotype.Service;

@Service
public class DefaultAddUserByUser implements AddUserByUser {
    @Override
    public void execute(Long userId, Long roomId) {

    }
}
