package br.com.crossgame.matchmaking.api.usecase;

import java.io.File;

public interface ExportTxt {
    File execute(Long idUser);
}
