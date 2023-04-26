package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.FeedbackController;
import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.api.usecase.CreateFeedbackForAUser;
import br.com.crossgame.matchmaking.api.usecase.DeleteUserFeedback;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllFeedbackByUserId;
import br.com.crossgame.matchmaking.api.usecase.UpdateUserFeedback;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(FeedbackController.class)
public class DefaultFeedbackController implements FeedbackController{

    private CreateFeedbackForAUser createFeedbackForAUser;
    private RetrieveAllFeedbackByUserId retrieveAllFeedbackByUserId;
    private UpdateUserFeedback updateUserFeedback;
    private DeleteUserFeedback deleteUserFeedback;

    @Override
    public UserAndFeedback createFeedbackForAUser(Long userId, Feedback feedback) {
        return this.createFeedbackForAUser.execute(userId, feedback);
    }

    @Override
    public List<Feedback> retrieveAllFeedbackByUserId(Long userId) {
        return this.retrieveAllFeedbackByUserId.execute(userId);
    }

    @Override
    public UserAndFeedback updateUserFeedback(Long userId, Feedback feedback) {
        return this.updateUserFeedback.execute(userId, feedback);
    }

    @Override
    public void deleteUserFeedback(Long userId, Long feedbackId) {
        this.deleteUserFeedback.execute(userId, feedbackId);
    }
}