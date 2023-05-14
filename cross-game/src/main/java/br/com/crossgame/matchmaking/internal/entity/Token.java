package br.com.crossgame.matchmaking.internal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {
    private String encodedToken;
}
