package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "preference")
@NoArgsConstructor
@Data
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "food")
    private FoodType food;

    @Enumerated(EnumType.STRING)
    @Column(name = "movie_genre")
    private MovieGenre movieGenre;

    @Enumerated(EnumType.STRING)
    @Column(name = "series_genre")
    private SeriesGenre seriesGenre;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_genre")
    private GameGenre gameGenre;

    @Enumerated(EnumType.STRING)
    @Column(name = "music_genre")
    private MusicGenre musicGenre;

    public Preference(FoodType food, MovieGenre movieGenre, SeriesGenre seriesGenre, GameGenre gameGenre) {
        this.food = food;
        this.movieGenre = movieGenre;
        this.seriesGenre = seriesGenre;
        this.gameGenre = gameGenre;
    }
}
