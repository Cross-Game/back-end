package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RemoveUserByAdmin;
import org.springframework.stereotype.Service;

@Service
public class DefaultRemoveUserByAdmin implements RemoveUserByAdmin {
    @Override
    public void execute(Long userId, Long adminId, Long idRoom) {

    }
}
