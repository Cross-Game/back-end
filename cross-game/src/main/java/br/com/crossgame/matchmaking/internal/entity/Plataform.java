package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.PlataformType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "plataform")
@NoArgsConstructor
@Data
public class Plataform implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "plataform_type")
    @Enumerated(EnumType.STRING)
    private PlataformType plataformType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "game_plataform",
            joinColumns = @JoinColumn(name = "plataform_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> games;

    public Plataform(PlataformType plataformType) {
        this.plataformType = plataformType;
    }
}
