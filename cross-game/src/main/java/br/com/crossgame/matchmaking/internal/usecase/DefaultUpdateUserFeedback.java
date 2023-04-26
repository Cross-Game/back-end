package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.api.usecase.UpdateUserFeedback;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.UserAndFeedbackRecordBuildUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultUpdateUserFeedback implements UpdateUserFeedback {

    private UserRepository userRepository;
    private RetrieveUserById retrieveUserById;

    @Override
    public UserAndFeedback execute(Long userId, Feedback feedback) {
        User user = this.retrieveUserById.execute(userId);

        Feedback feedbackFound = user.getFeedbacks().stream()
                .filter(fb -> fb.getId().equals(feedback.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int feedbackIndex = user.getFeedbacks().indexOf(feedbackFound);
        user.getFeedbacks().set(feedbackIndex, feedback);

        this.userRepository.save(user);
        log.info("Updating feedback for user with Id = {}", userId);
        return UserAndFeedbackRecordBuildUtil.transform(user, feedback);
    }

}
