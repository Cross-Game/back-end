package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.AddFriendToAnUser;
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
import java.time.LocalDate;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultAddFriendToAnUser implements AddFriendToAnUser {

    private UserRepository userRepository;

    private FriendsRepository friendsRepository;

    @Override
    public User execute(Long userId, Friends friend) {
        User userAddingAFriend = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User to add a friend not found"));

        User userToAddFound = userRepository.findByUsername(friend.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This username does not exists"));

        Friends addingFriend = new Friends(userToAddFound.getUsername(), LocalDate.now());

        if(userAddingAFriend.getUsername().equals(userToAddFound.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot add yourself as a friend");
        } else if(this.thisUserAlreadyHasThisFriend(userAddingAFriend, addingFriend)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You already has this user as a friend");
        }

        log.info(String.format("User with id = %s added %s as a friend", userId, addingFriend.getUsername()));
        userAddingAFriend.setFriends(addingFriend);
        userToAddFound.setFriends(new Friends(userAddingAFriend.getUsername(), LocalDate.now()));
        this.friendsRepository.save(addingFriend);
        return this.userRepository.save(userAddingAFriend);
    }

    private boolean thisUserAlreadyHasThisFriend(User user, Friends friend){
         return user.getFriends()
                 .stream()
                 .anyMatch(friends -> friends.getUsername().equals(friend.getUsername()));
    }
}
