package br.com.crossgame.matchmaking.internal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Description;
import lombok.Data;

import javax.persistence.*;


@Data
public class ImageGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Description("Identificador hash utilizado pela API IGDB")
    @JsonProperty("image_id")
    private String imageId;

    @Enumerated(EnumType.STRING)
    private TypeImage typeImage;

    private String link;
}
