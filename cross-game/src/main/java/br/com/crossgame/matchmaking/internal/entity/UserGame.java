package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.GameFunction;
import br.com.crossgame.matchmaking.internal.entity.enums.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_games")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "game_id")
    private GenericGame game;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "generic_game_id")
    private List<GenericGame> genericGames;

    public UserGame(Long id, boolean isFavoriteGame, String userNickname, String gamerId, SkillLevel skillLevel,
                    GameFunction gameFunction,
                    Game game,
                    User user) {
        this.id = id;
        this.isFavoriteGame = isFavoriteGame;
        this.userNickname = userNickname;
        this.gamerId = gamerId;
        this.skillLevel = skillLevel;
        this.gameFunction = gameFunction;
        this.game = game;
        this.user = user;
    }

    public UserGame(Long id, boolean isFavoriteGame, String userNickname, String gamerId, SkillLevel skillLevel,
                    GameFunction gameFunction,
                    Game game,
                    User user,
                    List<GenericGame> genericGames) {
        this.id = id;
        this.isFavoriteGame = isFavoriteGame;
        this.userNickname = userNickname;
        this.gamerId = gamerId;
        this.skillLevel = skillLevel;
        this.gameFunction = gameFunction;
        this.game = game;
        this.user = user;
        this.genericGames = genericGames;
    }

    public UserGame(Long id, boolean isFavoriteGame, String userNickname, String gamerId, SkillLevel skillLevel, GameFunction gameFunction) {
        this.id = id;
        this.isFavoriteGame = isFavoriteGame;
        this.userNickname = userNickname;
        this.gamerId = gamerId;
        this.skillLevel = skillLevel;
        this.gameFunction = gameFunction;
    }
}