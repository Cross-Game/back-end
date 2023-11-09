package br.com.crossgame.matchmaking.api.usecase;

public interface RetrieveAllFriendsByUserIdAndExportToCsvOrTxt {

    void execute(Long userId, String archiveType);
}
