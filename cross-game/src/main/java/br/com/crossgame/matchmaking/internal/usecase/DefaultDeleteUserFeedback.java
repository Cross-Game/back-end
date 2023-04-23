package br.com.crossgame.matchmaking.internal.usecase;


import br.com.crossgame.matchmaking.api.usecase.DeleteUserFeedback;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DefaultDeleteUserFeedback implements DeleteUserFeedback {

    private RetrieveUserById retrieveUserById;
    private FeedbackRepository feedbackRepository;

    @Override
    public void execute(Long userId, Long feedbackId) {
        User user = this.retrieveUserById.execute(userId);

        Feedback feedbackToDelete = user.getFeedbacks()
                .stream()
                .filter(feedback -> feedback.getId().equals(feedbackId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Feedback not found"));
        user.getFeedbacks().remove(feedbackToDelete);
        this.feedbackRepository.delete(feedbackToDelete);
    }
}
