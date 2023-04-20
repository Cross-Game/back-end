package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.api.usecase.CreateFeedbackForAUser;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.FeedbackRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DefaultCreateFeedbackForAUser implements CreateFeedbackForAUser {

    private UserRepository userRepository;
    private FeedbackRepository feedbackRepository;
    private RetrieveUserById retrieveUserById;

    @Override
    public UserAndFeedback execute(Long userId, Feedback feedback) {
        User user = this.retrieveUserById.execute(userId);

        if (user.getId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You cannot add a feedback for yourself.");
        }

        user.setFeedbacks(feedback);

        return ;
    }
}
