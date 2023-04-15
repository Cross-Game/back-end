package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.FriendsController;
import br.com.crossgame.matchmaking.api.usecase.AddFriendToAnUser;
import br.com.crossgame.matchmaking.api.usecase.DeleteFriend;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllFriendsByUserId;
import br.com.crossgame.matchmaking.internal.entity.Friends;
import br.com.crossgame.matchmaking.internal.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(FriendsController.class)
public class DefaultFriendsController implements FriendsController{

    private AddFriendToAnUser addFriendToAnUser;

    private RetrieveAllFriendsByUserId retrieveAllFriendsByUserId;

    private DeleteFriend deleteFriend;

    @Override
    public User addFriendToAnUser(Long userId, Friends friendToAdd) {
        return this.addFriendToAnUser.execute(userId, friendToAdd);
    }

    @Override
    public List<Friends> retrieveAllFriendsByUserId(Long userId) {
        return this.retrieveAllFriendsByUserId.execute(userId);
    }

    @Override
    public void deleteFriend(Long userId, String friendUserName) {
        this.deleteFriend.execute(userId, friendUserName);
    }
}
