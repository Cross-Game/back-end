package br.com.crossgame.matchmaking.internal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String roomName;
    @Min(0)
    @Max(10)
    private int capacity;
    @NotBlank
    private String gameName;
    private String rankGame;
    private int levelGame;
    private String description;
    private boolean isPrivate;
    private String tokenAccess;
}
