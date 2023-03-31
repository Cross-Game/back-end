package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
