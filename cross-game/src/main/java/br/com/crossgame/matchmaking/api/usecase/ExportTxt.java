package br.com.crossgame.matchmaking.api.usecase;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


public interface
ExportTxt {
    ResponseEntity<Resource> execute(Long idUser);
}
