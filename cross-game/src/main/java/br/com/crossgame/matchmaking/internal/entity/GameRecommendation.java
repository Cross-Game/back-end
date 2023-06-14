package br.com.crossgame.matchmaking.internal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GameRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String gameName;
    private String company;
    private String reason;
    private String genre;
}
