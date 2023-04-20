package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.FriendsController;
import br.com.crossgame.matchmaking.api.usecase.AddFriendToAnUser;
import br.com.crossgame.matchmaking.api.usecase.DeleteFriend;
import br.com.crossgame.matchmaking.api.usecase.GenerateFiles;
import br.com.crossgame.matchmaking.api.usecase.RetrieveAllFriendsByUserId;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
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

    private AddFriendToAnUser addFriendToAnUser;

    private RetrieveAllFriendsByUserId retrieveAllFriendsByUserId;

    private DeleteFriend deleteFriend;

    private GenerateFiles generateFiles;

    @Override
    public User addFriendToAnUser(Long userId, Friend friendToAdd) {
        return this.addFriendToAnUser.execute(userId, friendToAdd);
    }

    @Override
    public List<Friend> retrieveAllFriendsByUserId(Long userId) {
        return this.retrieveAllFriendsByUserId.execute(userId);
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
}
