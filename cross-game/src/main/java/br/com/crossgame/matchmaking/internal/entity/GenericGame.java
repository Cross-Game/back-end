package br.com.crossgame.matchmaking.internal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

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
}
