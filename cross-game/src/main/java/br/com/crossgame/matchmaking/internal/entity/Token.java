package br.com.crossgame.matchmaking.internal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Token implements Serializable {
    private String encodedToken;
}
