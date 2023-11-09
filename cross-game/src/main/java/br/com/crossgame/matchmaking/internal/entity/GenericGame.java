package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class GenericGame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String gameName;

    @JsonProperty("platforms")
    @ElementCollection
    @CollectionTable(name = "platforms_id", joinColumns = @JoinColumn(name = "game_id"))
    private List<Integer> platformsId;


    @ElementCollection(targetClass = GameplayPlatformType.class)
    @CollectionTable(name = "game_platforms_type", joinColumns = @JoinColumn(name = "game_id"))
    @Enumerated(EnumType.STRING)
    private List<GameplayPlatformType> platformsType;

    @JsonProperty("cover")
    private Integer coverId;

    @Transient
    private ImageGame imageGame;

    @JsonProperty("genres")
    @ElementCollection
    @CollectionTable(name = "game_genres_id", joinColumns = @JoinColumn(name = "game_id"))
    private List<Integer> genreId;

    @ElementCollection(targetClass = GameGenre.class)
    @CollectionTable(name = "game_genre_type", joinColumns = @JoinColumn(name = "game_id"))
    @Enumerated(EnumType.STRING)
    private List<GameGenre> gameGenres;

}
