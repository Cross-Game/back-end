package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.SkillLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user_games")
@NoArgsConstructor
@Data
public class UserGame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "is_favorite_game")
    private boolean isFavoriteGame;

    @NotBlank
    @Column(name = "user_nickname")
    private String userNickname;

    @NotBlank
    @Column(name = "gamer_id")
    private String gamerId;

    @NotNull
    @Column(name = "skill_level")
    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    public UserGame(Long id, boolean isFavoriteGame, String userNickname, String gamerId, SkillLevel skillLevel,
                    Game game,
                    User user) {
        this.id = id;
        this.isFavoriteGame = isFavoriteGame;
        this.userNickname = userNickname;
        this.gamerId = gamerId;
        this.skillLevel = skillLevel;
        this.game = game;
        this.user = user;
    }

    public UserGame(Long id, boolean isFavoriteGame, String userNickname, String gamerId, SkillLevel skillLevel) {
        this.id = id;
        this.isFavoriteGame = isFavoriteGame;
        this.userNickname = userNickname;
        this.gamerId = gamerId;
        this.skillLevel = skillLevel;
    }
}