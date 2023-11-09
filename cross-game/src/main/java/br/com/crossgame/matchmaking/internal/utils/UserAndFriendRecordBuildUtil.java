package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.api.model.UserAndFriend;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserAndFriendRecordBuildUtil {

    public UserAndFriend transform(User user, Friend friend){
        return new UserAndFriend(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isOnline(),
                friend);
    }
}
