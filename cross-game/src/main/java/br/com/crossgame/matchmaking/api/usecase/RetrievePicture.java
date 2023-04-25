package br.com.crossgame.matchmaking.api.usecase;

import org.springframework.http.ResponseEntity;

public interface RetrievePicture {
    ResponseEntity<byte[]> execute(Long id);
}
