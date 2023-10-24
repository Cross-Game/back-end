package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserFilterController;
import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllUsersWithFilter;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import br.com.crossgame.matchmaking.internal.utils.FilaObj;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(UserFilterController.class)
public class DefaultUserFilterController implements UserFilterController{

    private RetrieveAllUsersWithFilter retrieveAllUsersWithFilter;

    @Override
    public FilaObj<UserData> retrieveAllUsersWithFilter(SkillLevel skillLevel,
                                                        String gameName,
                                                        Preferences preferences,
                                                        Preferences preferences2,
                                                        Preferences preferences3,
                                                        boolean skillLevelFeedback,
                                                        boolean behaviorFeedback) {
        return this.retrieveAllUsersWithFilter.execute(skillLevel,
                gameName,
                preferences,
                preferences2,
                preferences3,
                skillLevelFeedback,
                behaviorFeedback);
    }
}
