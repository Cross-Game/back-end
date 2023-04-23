package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserAndFeedbackRecordBuildUtil {

    public UserAndFeedback transform(User user, Feedback feedback){
        return new UserAndFeedback(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isOnline(),
                feedback);
    }
}
