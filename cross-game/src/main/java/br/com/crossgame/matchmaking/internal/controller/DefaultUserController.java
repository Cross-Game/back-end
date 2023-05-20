package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserController;
import br.com.crossgame.matchmaking.api.model.UserCompleteDataResponse;
import br.com.crossgame.matchmaking.api.model.UserCreate;
import br.com.crossgame.matchmaking.api.model.UserData;
import br.com.crossgame.matchmaking.api.model.UserDataForLoginServices;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.utils.UserCompleteDataResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(UserController.class)
public class DefaultUserController implements UserController {

    private CreateUser createOrUpdateUser;
    private DeleteUserById deleteUserById;
    private RetrieveAllUsers retriveAllUsers;
    private RetrieveUserById retrieveUserById;
    private UpdateUser updateUser;
    private AddPictureOnUser addPictureOnUser;
    private RetrievePicture retrievePicture;
    private ValidateNickname validateNickname;
    private UpdatePasswordByUsernameEmailForLoginServices updatePasswordByUsernameEmailForLoginServices;

    @Override
    public User createUser(UserCreate userCreate) {
        return this.createOrUpdateUser.execute(userCreate);
    }

    @Override
    public List<UserData> retrieveAllUsers() {
        return this.retriveAllUsers.execute();
    }

    @Override
    public UserCompleteDataResponse retrieveUsersById(Long id) {
        return UserCompleteDataResponseBuildUtils.transform(this.retrieveUserById.execute(id));
    }

    @Override
    public User updateUser(UserCreate userCreate) {
        return this.updateUser.execute(userCreate);
    }

    @Override
    public void deleteUserById(Long id) {
        this.deleteUserById.execute(id);
    }

    @Override
    public void addPicture(Long id, byte[] picture) {
        this.addPictureOnUser.execute(id, picture);
    }

    @Override
    public ResponseEntity<byte[]> retrievePicture(Long id) {
        return this.retrievePicture.execute(id);
    }

    @Override
    public Boolean validateByNickname(String username){
           return this.validateNickname.execute(username);
    }

    @Override
    public void updatePasswordByUsernameEmailForLoginServices(UserDataForLoginServices userDataForLoginServices) {
        this.updatePasswordByUsernameEmailForLoginServices.execute(userDataForLoginServices);
    }
}
