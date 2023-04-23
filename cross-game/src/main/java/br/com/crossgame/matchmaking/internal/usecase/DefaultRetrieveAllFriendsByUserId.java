package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveAllFriendsByUserId;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultRetrieveAllFriendsByUserId implements RetrieveAllFriendsByUserId {

    private UserRepository userRepository;

    @Override
    public List<Friend> execute(Long userId) {
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

         if (user.getFriends().isEmpty()){
             throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                     String.format("User with id = %d has no friends", user.getId()));
         }

         return user.getFriends();
    }
}
