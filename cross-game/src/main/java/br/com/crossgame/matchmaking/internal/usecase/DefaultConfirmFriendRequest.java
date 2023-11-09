package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserAndFriend;
import br.com.crossgame.matchmaking.api.usecase.ConfirmFriendRequest;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.FriendshipState;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.UserAndFriendRecordBuildUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class DefaultConfirmFriendRequest implements ConfirmFriendRequest {

    private UserRepository userRepository;
    @Override
    public UserAndFriend execute(Long userId, String friendUsername) {
        User userConfirmFriendRequest = this.userRepository
                .findByIdAndFriendsFriendshipState(userId, FriendshipState.PENDING)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "You dont have any friend request from this username"));

        Friend friendRequestFound = this.searchFriendRequestByUsername(userConfirmFriendRequest, friendUsername);
        int userFriendIndex = userConfirmFriendRequest.getFriends().indexOf(friendRequestFound);
        friendRequestFound.setFriendshipState(FriendshipState.CONFIRMED);
        userConfirmFriendRequest.getFriends().set(userFriendIndex, friendRequestFound);

        User friendAsAUser = this.findUserAsAFriend(friendRequestFound.getUsername());

        Friend findingUserOnFriendFriendlist = this.searchFriendRequestByUsername(friendAsAUser,
                userConfirmFriendRequest.getUsername());
        int userOnFriendFriendlistIndex = friendAsAUser.getFriends().indexOf(findingUserOnFriendFriendlist);
        findingUserOnFriendFriendlist.setFriendshipState(FriendshipState.CONFIRMED);
        friendAsAUser.getFriends().set(userOnFriendFriendlistIndex, findingUserOnFriendFriendlist);

        List<User> usersConfirmingFriendship = Arrays.asList(userConfirmFriendRequest, friendAsAUser);

        this.userRepository.saveAll(usersConfirmingFriendship);
        log.info("User with id = {} accepted {} as a friend", userId, friendUsername);
        return UserAndFriendRecordBuildUtil.transform(userConfirmFriendRequest, friendRequestFound);
    }

    private User findUserAsAFriend(String username){
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This username does not exists"));
    }

    private Friend searchFriendRequestByUsername(User user, String friendUsername){
        return user.getFriends()
                .stream()
                .filter(friend -> friend.getUsername().equals(friendUsername))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,
                        String.format("You dont have any friend request from a username = %s", friendUsername)
                ));
    }
}
