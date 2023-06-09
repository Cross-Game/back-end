package br.com.crossgame.matchmaking.api.usecase;

public interface RemoveUserByAdmin {
    public void execute(Long userId, Long adminId,Long idRoom);
}
