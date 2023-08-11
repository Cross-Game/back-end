package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.FriendsController;
import br.com.crossgame.matchmaking.api.model.UserAndFriend;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@ConditionalOnSingleCandidate(FriendsController.class)
public class DefaultFriendsController implements FriendsController{

    private SendFriendRequestToAnUser sendFriendRequestToAnUser;
    private RetrieveAllFriendsByUserId retrieveAllFriendsByUserId;
    private DeleteFriend deleteFriend;
    private ConfirmFriendRequest confirmFriendRequest;
    private DecliningFriendRequest decliningFriendRequest;

    @Override
    public UserAndFriend addFriendToAnUser(Long userId, Friend friendToAdd) {
        return this.sendFriendRequestToAnUser.execute(userId, friendToAdd);
    }
    @Override
    public void deleteFriend(Long userId, String friendUsername) {
        this.deleteFriend.execute(userId, friendUsername);
    }

    @Override
    public UserAndFriend confirmFriendRequest(Long userId, String friendUsername) {
        return this.confirmFriendRequest.execute(userId, friendUsername);
    }

    @Override
    public void decliningFriendRequest(Long userId, String friendUsername) {
        this.decliningFriendRequest.execute(userId, friendUsername);
    }
    @Override
    public List<Friend> retrieveAllFriendsByUserId(Long userId) {
        return retrieveAllFriendsByUserId.execute(userId);
    }
}
