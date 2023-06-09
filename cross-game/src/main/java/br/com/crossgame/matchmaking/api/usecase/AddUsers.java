package br.com.crossgame.matchmaking.api.usecase;


public interface AddUsers {
    void execute(Long userId, Long adminId,Long roomId);
}
