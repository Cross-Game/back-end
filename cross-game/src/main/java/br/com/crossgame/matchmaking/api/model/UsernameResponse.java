package br.com.crossgame.matchmaking.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsernameResponse(@JsonProperty("name")String name, @JsonProperty("summonerLevel") int summonerLevel) {
}
