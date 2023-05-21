package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Long> {
}
