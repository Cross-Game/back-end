package br.com.crossgame.matchmaking.api.usecase;

public interface DeletePreferenceForUserById {

    void execute(Long userId, Long preferenceId);
}