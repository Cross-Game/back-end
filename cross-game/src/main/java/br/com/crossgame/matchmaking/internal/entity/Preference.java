package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "preference")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Preference {

    public Preference() {
        food = new ArrayList<>();
        movieGenre = new ArrayList<>();
        seriesGenre = new ArrayList<>();
        gameGenre = new ArrayList<>();
        musicGenre = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ElementCollection(targetClass = FoodType.class)
    @Enumerated(EnumType.STRING)
    private List<FoodType> food;

    @ElementCollection(targetClass = MovieGenre.class)
    @Enumerated(EnumType.STRING)
    private List<MovieGenre> movieGenre;

    @ElementCollection(targetClass = SeriesGenre.class)
    @Enumerated(EnumType.STRING)
    private List<SeriesGenre> seriesGenre;

    @ElementCollection(targetClass = GameGenre.class)
    @Enumerated(EnumType.STRING)
    private List<GameGenre> gameGenre;

    @ElementCollection(targetClass = MusicGenre.class)
    @Enumerated(EnumType.STRING)
    private List<MusicGenre> musicGenre;
}
