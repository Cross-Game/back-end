package br.com.crossgame.matchmaking.api.usecase;

public interface RetrieveAnExistentUserIdForLoginServices {

    Long execute(String username, String email);
}
