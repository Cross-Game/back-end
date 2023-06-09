package br.com.crossgame.matchmaking.api.usecase;

public interface RemoveUser {
    void execute(Long userId, Long roomId);
}
