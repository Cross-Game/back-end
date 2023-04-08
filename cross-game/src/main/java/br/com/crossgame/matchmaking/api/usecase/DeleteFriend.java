package br.com.crossgame.matchmaking.api.usecase;

public interface DeleteFriend {

    void execute(Long userId, String friendUsername);
}
