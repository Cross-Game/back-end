package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.DeleteFriend;
import br.com.crossgame.matchmaking.internal.entity.Friends;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.FriendsRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultDeleteFriend implements DeleteFriend {

    private UserRepository userRepository;
    private FriendsRepository friendsRepository;

    @Override
    public void execute(Long userId, String friendUsername) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Friends friendToDelete = this.friendsRepository.findByUser(user)
                .stream()
                .filter(friend -> friend.getUsername().equals(friendUsername))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This friendship does not exists"));

        log.info("%s deleted %s as a friend", user.getUsername(), friendToDelete.getUsername());
        user.getFriends().remove(friendToDelete);
        this.friendsRepository.delete(friendToDelete);
    }

}
