package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

@Entity
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
