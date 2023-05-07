package br.com.crossgame.matchmaking.api.usecase;

public interface DeleteLinkedGameToUser {
    void execute(Long userId, Long userGameId);
}
