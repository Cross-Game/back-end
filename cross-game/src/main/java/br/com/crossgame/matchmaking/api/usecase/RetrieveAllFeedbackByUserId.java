package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.Feedback;

import java.util.List;

public interface RetrieveAllFeedbackByUserId {

    List<Feedback> execute(Long userId);
}
