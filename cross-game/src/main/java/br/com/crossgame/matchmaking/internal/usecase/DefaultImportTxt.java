package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ImportTxt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@Service
public class DefaultImportTxt implements ImportTxt {
    @Override
    public void execute(MultipartFile file, Long idUser) {

    }


}