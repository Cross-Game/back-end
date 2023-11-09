package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.api.model.UsernameResponse;
import org.springframework.http.ResponseEntity;

public interface ValidateUsername {
    public ResponseEntity<UsernameResponse> execute(Long id, String username,String gameName);
}
