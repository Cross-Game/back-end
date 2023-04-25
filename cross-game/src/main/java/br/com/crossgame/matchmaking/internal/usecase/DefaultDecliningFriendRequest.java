package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.DecliningFriendRequest;
import br.com.crossgame.matchmaking.api.usecase.DeleteFriend;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.FriendshipState;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultDecliningFriendRequest implements DecliningFriendRequest {

    private DeleteFriend deleteFriend;
    private UserRepository userRepository;

    @Override
    public void execute(Long userId, String friendUsername) {
        User user = this.userRepository.findByIdAndFriendsFriendshipState(userId, FriendshipState.PENDING)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Friend friendToDelete =user.getFriends()
                .stream()
                .filter(friend -> friend.getUsername().equals(friendUsername))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Friend not found"));

        log.info("User with id = {} decline friend request from {}.", userId, friendUsername);
        this.deleteFriend.execute(userId, friendToDelete.getUsername());
    }
}
