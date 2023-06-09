package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;

public interface AddUserByUser {
     void execute(Long userId, Long roomId);
}
