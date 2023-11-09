package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.GameplayPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameplayPlatformRepository extends JpaRepository<GameplayPlatform, Long> {

}
