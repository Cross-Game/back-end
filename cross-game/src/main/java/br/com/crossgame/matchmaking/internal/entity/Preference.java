package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.FoodType;
import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;
import br.com.crossgame.matchmaking.internal.entity.enums.MovieGenre;
import br.com.crossgame.matchmaking.internal.entity.enums.SeriesGenre;
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

    @Column(name = "food")
    private FoodType food;

    @Column(name = "movie_genre")
    private MovieGenre movieGenre;

    @Column(name = "series_genre")
    private SeriesGenre seriesGenre;

    @Column(name = "game_genre")
    private GameGenre gameGenre;
}
