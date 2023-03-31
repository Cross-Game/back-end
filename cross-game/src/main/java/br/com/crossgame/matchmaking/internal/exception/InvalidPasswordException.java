package br.com.crossgame.matchmaking.internal.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super("Invalid password");
    }
}
