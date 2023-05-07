package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.GameMode;
import br.com.crossgame.matchmaking.internal.entity.enums.GameRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "games")
@NoArgsConstructor
@Data
public class GameModeAndRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "game_mode")
    @Enumerated(EnumType.STRING)
    private GameMode gameMode;

    @NotNull
    @Column(name = "game_role")
    @Enumerated(EnumType.STRING)
    private GameRole gameRole;
}
