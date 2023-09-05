package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenericGamesRepository extends JpaRepository<GenericGame,Long> {
    public Optional<GenericGame> findByGameNameContains(String gameName);
    boolean existsByGameName(String gameName);
}
