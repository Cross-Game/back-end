package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.internal.entity.Feedback;

public interface CreateFeedbackForAUser {

    UserAndFeedback execute(Long userId, Feedback feedback);
}
