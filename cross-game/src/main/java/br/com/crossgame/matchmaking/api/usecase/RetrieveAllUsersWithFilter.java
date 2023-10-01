package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.internal.entity.enums.*;
import br.com.crossgame.matchmaking.internal.utils.FilaObj;

public interface RetrieveAllUsersWithFilter {

    FilaObj<UserData> execute(SkillLevel skillLevel,
                              GameFunction gameFunction,
                              String gameName,
                              Preferences preferences,
                              Preferences preferences2,
                              Preferences preferences3,
                              boolean skillLevelFeedback,
                              boolean behaviorFeedback);
}
