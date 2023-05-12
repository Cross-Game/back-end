package br.com.crossgame.matchmaking.internal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException extends ResponseStatusException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    public InvalidPasswordException() {
        super(HTTP_STATUS, "Password doesn't match");
    }
}
