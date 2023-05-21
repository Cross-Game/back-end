package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.api.model.GameResponse;
import br.com.crossgame.matchmaking.api.model.PlataformResponse;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.Plataform;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class GameResponseBuildUtils {

    public static GameResponse transform(Game game){
        return new GameResponse(game.getId(),
                game.getGameName(),
                game.getGameGenre(),
                transformPlataformToPlataformResponse(game.getPlataforms()));
    }

    public static List<GameResponse> transform(List<Game> games){
        List<GameResponse> gameResponses = new ArrayList<>();
        for(Game game : games){
            gameResponses.add(new GameResponse(
                    game.getId(),
                    game.getGameName(),
                    game.getGameGenre(),
                    transformPlataformToPlataformResponse(game.getPlataforms())));
        }
        return gameResponses;
    }

    private List<PlataformResponse> transformPlataformToPlataformResponse(List<Plataform> plataforms){
        List<PlataformResponse> plataformResponses = new ArrayList<>();
        for (Plataform plataform : plataforms){
            plataformResponses.add(new PlataformResponse(plataform.getId(),
                    plataform.getPlataformType()));
        }
        return plataformResponses;
    }
}
