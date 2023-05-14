package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
@NoArgsConstructor
@Data
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "game_name")
    private String gameName;

    @NotNull
    @Column(name = "game_genre")
    @Enumerated(EnumType.STRING)
    private GameGenre gameGenre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game",
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<UserGame> usersGame;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "game_plataform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "plataform_id"))
    private List<Plataform> plataforms;

    public Game(Long id, String gameName, GameGenre gameGenre) {
        this.id = id;
        this.gameName = gameName;
        this.gameGenre = gameGenre;
    }

    public void setUsersGame(UserGame usersGame) {
        if (this.usersGame == null){
            this.usersGame = new ArrayList<>();
        }
        this.usersGame.add(usersGame);
    }

    public void setPlataforms(Plataform plataform) {
        if(this.plataforms == null){
            this.plataforms = new ArrayList<>();
        }
        this.plataforms.add(plataform);
    }
}
