package br.com.crossgame.matchmaking.api.model;

public record TokenResponse(String access_token, Integer expires_in, String token_type) {
}
