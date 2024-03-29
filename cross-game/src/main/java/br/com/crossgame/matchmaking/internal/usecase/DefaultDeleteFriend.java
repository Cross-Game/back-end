package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.DeleteFriend;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.FriendRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultDeleteFriend implements DeleteFriend {

    private UserRepository userRepository;
    private FriendRepository friendRepository;

    @Override
    public void execute(Long userId, String friendUsername) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Friend friendToDelete = user.getFriends()
                .stream()
                .filter(friend -> friend.getUsername().equals(friendUsername))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This friendship does not exists"));

        log.info(String.format("%s deleted %s as a friend", user.getUsername(), friendToDelete.getUsername()));

        user.getFriends().remove(friendToDelete);
        this.friendRepository.delete(friendToDelete);

        this.friendDeletingUser(friendUsername, user);
    }

    private void friendDeletingUser(String friendUsername, User user){
        User friendUser = this.userRepository.findByUsername(friendUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Friend friendDeletingUser = friendUser.getFriends()
                .stream()
                .filter(friend -> friend.getUsername().equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This friendship does not exists"));

        friendUser.getFriends().remove(friendDeletingUser);
        this.friendRepository.delete(friendDeletingUser);
    }
}
