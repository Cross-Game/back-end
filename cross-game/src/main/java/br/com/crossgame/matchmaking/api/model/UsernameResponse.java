package br.com.crossgame.matchmaking.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsernameResponse(@JsonProperty("id") String id,
                               @JsonProperty("name")String name,
                               @JsonProperty("accountId")String accountId,
                               @JsonProperty("puuid")String puuid,
                               @JsonProperty("tier")String tier,
                               @JsonProperty("summonerLevel") int summonerLevel) {
}
