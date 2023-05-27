package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.enums.FriendshipState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByIdAndFriendsFriendshipState(Long userId, FriendshipState friendshipState);

    Optional<User> findByUsernameAndEmail(String username, String email);

    @Modifying
    @Transactional
    @Query("update User u set u.profilePicture = ?2 where u.id = ?1")
    void setProfilePicture(Long id, byte[] picture);

    @Query("select u.profilePicture from User u where u.id = ?1")
    byte[] getProfilePicture(Long id);
}
