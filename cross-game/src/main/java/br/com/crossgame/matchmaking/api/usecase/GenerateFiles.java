package br.com.crossgame.matchmaking.api.usecase;

import java.io.IOException;

public interface GenerateFiles {
    public void execute(Long userId,String archiveType) throws IOException;
}
