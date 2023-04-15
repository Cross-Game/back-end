package br.com.crossgame.matchmaking.internal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException extends ResponseStatusException {
    public InvalidPasswordException(HttpStatus status) {
        super(status, "Password doesn't match");
    }
}
