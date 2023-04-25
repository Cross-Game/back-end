package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveAllFeedbackByUserId;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllFeedbackByUserId implements RetrieveAllFeedbackByUserId {

    private RetrieveUserById retrieveUserById;

    @Override
    public List<Feedback> execute(Long userId) {
        User user = this.retrieveUserById.execute(userId);

        if (user.getFeedbacks().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    String.format("User with id = %d has no feedbacks", user.getId()));
        }

        return user.getFeedbacks();
    }
}
