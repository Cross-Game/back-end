package br.com.crossgame.matchmaking.api.usecase;

public interface RemoveUserByUser {
    void execute(Long userId, Long roomId);
}
