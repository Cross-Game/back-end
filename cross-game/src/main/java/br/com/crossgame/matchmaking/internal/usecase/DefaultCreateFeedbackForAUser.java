package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.api.usecase.CreateFeedbackForAUser;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.UserAndFeedbackRecordBuildUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class DefaultCreateFeedbackForAUser implements CreateFeedbackForAUser {

    private UserRepository userRepository;
    private RetrieveUserById retrieveUserById;

    @Override
    public UserAndFeedback execute(Long userId, Feedback feedback) {
        User user = this.retrieveUserById.execute(userId);

        if (user.getUsername().equals(feedback.getUserGivenFeedback())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You cannot add a feedback for yourself.");
        }

        feedback.setFeedbackGivenDate(LocalDate.now());
        user.setFeedbacks(feedback);

        this.userRepository.save(user);
        log.info("User {} is give a feedback to {}", feedback.getUserGivenFeedback(), user.getUsername());

        return UserAndFeedbackRecordBuildUtil.transform(user, feedback);
    }
}
