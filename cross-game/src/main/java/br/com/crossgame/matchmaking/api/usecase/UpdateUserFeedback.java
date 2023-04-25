package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.internal.entity.Feedback;

public interface UpdateUserFeedback {

    UserAndFeedback execute(Long userId, Feedback feedback);
}
