package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.FriendsController;
import br.com.crossgame.matchmaking.api.model.UserAndFriend;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.usecase.DefaultOrderListByName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@ConditionalOnSingleCandidate(FriendsController.class)
public class DefaultFriendsController implements FriendsController{

    private SendFriendRequestToAnUser sendFriendRequestToAnUser;
    private RetrieveAllFriendsByUserId retrieveAllFriendsByUserId;
    private DeleteFriend deleteFriend;
    private GenerateFiles generateFiles;
    private ConfirmFriendRequest confirmFriendRequest;
    private DecliningFriendRequest decliningFriendRequest;

    @Override
    public UserAndFriend addFriendToAnUser(Long userId, Friend friendToAdd) {
        return this.sendFriendRequestToAnUser.execute(userId, friendToAdd);
    }
    @Override
    public void deleteFriend(Long userId, String friendUserName) {
        this.deleteFriend.execute(userId, friendUserName);
    }

    @Override
    public List<Friend> retrieveAllFriendsByUserIdAndExportToCsvOrTxt(Long userId, String archiveType) {
        try {
            this.generateFiles.execute(userId, archiveType);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return this.retrieveAllFriendsByUserId.execute(userId);
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
        DefaultOrderListByName orderListByName = new DefaultOrderListByName();
        List<Friend> friendsList = retrieveAllFriendsByUserId.execute(userId);
        return orderListByName.execute(friendsList);

    }
}
