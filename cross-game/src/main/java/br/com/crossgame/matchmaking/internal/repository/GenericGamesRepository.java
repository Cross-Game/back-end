package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenericGamesRepository extends JpaRepository<GenericGame,Long> {
    public Optional<List<GenericGame>> findByGameNameContains(String gameName);
    boolean existsByGameNameLike(String gameName);

    @Override
    boolean existsById(Long aLong);
}
