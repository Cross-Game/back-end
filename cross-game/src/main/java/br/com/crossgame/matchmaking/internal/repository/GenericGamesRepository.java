package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenericGamesRepository extends JpaRepository<GenericGame,Long> {
    public Optional<List<GenericGame>> findByGameNameContainsIgnoreCase(String gameName);
    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM GenericGame g WHERE LOWER(g.gameName) LIKE LOWER(CONCAT('%', :gameName, '%'))")
    boolean existsByGameNameLikeIgnoreCase(@Param("gameName") String gameName);


    @Override
    boolean existsById(Long aLong);
}
