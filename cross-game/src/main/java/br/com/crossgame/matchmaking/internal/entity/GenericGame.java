package br.com.crossgame.matchmaking.internal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class GenericGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String gameName;

    private String platform;

    @JsonProperty("cover")
    private Integer coverId;

    @Transient
    private ImageGame imageGame;
    private List<GameGenre> genre;
}
