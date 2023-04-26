package br.com.crossgame.matchmaking.api.usecase;

public interface DecliningFriendRequest {
    void execute(Long userId, String friendUsername);
}
