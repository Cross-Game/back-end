package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.UserController;
import br.com.crossgame.matchmaking.api.usecase.CreateOrUpdateUser;
import br.com.crossgame.matchmaking.api.usecase.DeleteUserById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.api.usecase.RetriveAllUsers;
import br.com.crossgame.matchmaking.internal.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(UserController.class)
public class DefaultUserController implements UserController{

    private CreateOrUpdateUser createOrUpdateUser;
    private DeleteUserById deleteUserById;
    private RetriveAllUsers retriveAllUsers;
    private RetrieveUserById retrieveUserById;

    @Override
    public User createUser(User user) {
        return this.createOrUpdateUser.execute(user);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return this.retriveAllUsers.execute();
    }

    @Override
    public User retrieveUsersById(Long id) {
        return this.retrieveUserById.execute(id);
    }

    @Override
    public User updateUser(User user) {
        return this.createOrUpdateUser.execute(user);
    }

    @Override
    public void deleteUserById(Long id) {
        this.deleteUserById.execute(id);
    }
}
