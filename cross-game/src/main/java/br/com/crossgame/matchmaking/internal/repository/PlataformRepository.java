package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.Plataform;
import br.com.crossgame.matchmaking.internal.entity.enums.PlataformType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlataformRepository extends JpaRepository<Plataform, Long> {

    Optional<Plataform> findByPlataformType(PlataformType plataformType);
}
