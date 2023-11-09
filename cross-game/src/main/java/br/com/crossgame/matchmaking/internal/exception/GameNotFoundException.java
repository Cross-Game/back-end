package br.com.crossgame.matchmaking.internal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameNotFoundException extends ResponseStatusException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public GameNotFoundException(HttpStatus status, String reason) {
        super(HTTP_STATUS, reason);
    }
}
