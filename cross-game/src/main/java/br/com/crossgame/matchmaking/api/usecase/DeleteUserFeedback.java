package br.com.crossgame.matchmaking.api.usecase;

public interface DeleteUserFeedback {
    void execute(Long userId, Long feedbackId);
}
