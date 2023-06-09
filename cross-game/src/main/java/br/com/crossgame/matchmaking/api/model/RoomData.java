package br.com.crossgame.matchmaking.api.model;

import java.util.List;

public record RoomData(Long id, String name, int capacity, String gameName, String rankGame, int levelGame,
                       List<UserData> user,boolean isPrivate, String tokenAccess, String description,boolean isTerminated,Long idUserAdmin) {
}
