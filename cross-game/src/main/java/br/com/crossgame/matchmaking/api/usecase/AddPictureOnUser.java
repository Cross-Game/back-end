package br.com.crossgame.matchmaking.api.usecase;

public interface AddPictureOnUser {

    void execute(Long id, byte[] picture);

}
