package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UserDataForLoginServices;

public interface UpdatePasswordByUsernameEmailForLoginServices {

    void execute(UserDataForLoginServices userDataForLoginServices);
}
