package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import br.com.crossgame.matchmaking.internal.entity.enums.PlataformType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
public class GameplayPlatform implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_plataform_type")
    @Enumerated(EnumType.STRING)
    private GameplayPlatformType gameplayPlataformType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameplayPlatformType getGameplayPlataformType() {
        return gameplayPlataformType;
    }

    public void setGameplayPlataformType(GameplayPlatformType gameplayPlataformType) {
        this.gameplayPlataformType = gameplayPlataformType;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPlatforms().add(this);
    }
}
