package br.com.crossgame.matchmaking.api.usecase;

public interface DeletePreferenceForUserByName {
    void execute(Long userId, String name);
}
