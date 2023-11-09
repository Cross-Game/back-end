package br.com.crossgame.matchmaking.api.usecase;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ImportTxt {
    public void execute(MultipartFile file, Long idUser);
}
