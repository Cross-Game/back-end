package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class QueryBuilder {

    private static String query = "SELECT u FROM User u";

    private static List<Preference> preferences;

    private static List<UserGame> userGames;

    private static List<Game> games;

    private static int whereCount = 0;


    public static String createQuery(){
        resetQuery();
        addJoinClausuleOnQuery();
        query += " WHERE";
        whereCount++;
        addUsergameAttributesOnQuery();
        addGameAttributesOnQuery();
        addPreferenceAttributesOnQuery();
        return query;
    }

    public static void clearList(){
        if (games != null && preferences != null && userGames != null){
            games.clear();
            preferences.clear();
            userGames.clear();
        }
    }

    private void resetQuery(){
        whereCount = 0;
        query = "SELECT u FROM User u";
    }

    private boolean verifyIfExisteWhereToAddAndClausule(){
        return whereCount > 2;
    }

    private void addAndClausuleOnQuery(){
        if (verifyIfExisteWhereToAddAndClausule()){
            query += " AND";
        }
    }

    private void addJoinClausuleOnQuery(){
        if (!userGames.isEmpty()){
            for (UserGame userGame : userGames) {
                if (!Objects.isNull(userGame.getSkillLevel()) || !Objects.isNull(userGame.getGameFunction())) {
                    query += " JOIN u.userGames ug";
                }
            }
        }
        if (!games.isEmpty()){
            for (Game game : games) {
                if (!Objects.isNull(game.getGameName()) || !Objects.isNull(game.getGameGenre())) {
                    if (query.contains(" JOIN u.userGames ug")){
                        query += " JOIN ug.game g";
                    } else {
                        query += " JOIN u.userGames ug JOIN ug.game g";
                    }
                }
            }
        }
        if(!preferences.isEmpty()) {
            for (Preference preference : preferences) {
                if (!Objects.isNull(preference.getPreferences())){
                        query += " JOIN u.preferences p";
                }
            }
        }
    }

    private void addUsergameAttributesOnQuery(){
        if (!userGames.isEmpty()){
            for (UserGame userGame : userGames){
                if (!Objects.isNull(userGame.getSkillLevel())){
                    whereCount++;
                    addAndClausuleOnQuery();
                    query += String.format(" ug.skillLevel = '%s'",
                            userGame.getSkillLevel().name());
                }
                if (!Objects.isNull(userGame.getGameFunction())){
                    whereCount++;
                    addAndClausuleOnQuery();
                    query += String.format(" ug.gameFunction = '%s'",
                            userGame.getGameFunction().name());
                }
            }
        }
    }

    private void addGameAttributesOnQuery(){
        if (!games.isEmpty()){
            for (Game game : games){
                if (!Objects.isNull(game.getGameName())){
                    whereCount++;
                    addAndClausuleOnQuery();
                    query += String.format(" g.gameName = '%s'",
                            game.getGameName());
                }
                if (!Objects.isNull(game.getGameGenre())){
                    whereCount++;
                    addAndClausuleOnQuery();
                    query += String.format(" g.gameGenre = '%s'",
                            game.getGameGenre().name());
                }
            }
        }
    }

    private void addPreferenceAttributesOnQuery(){
        if(!preferences.isEmpty()){
            for(Preference preference : preferences){
                if (!Objects.isNull(preference.getPreferences())){
                    whereCount++;
                    addAndClausuleOnQuery();
                    query += String.format(" p.preferences = '%s'",
                            preference.getPreferences());
                }
            }
        }
    }

    public static void setPreferences(Preference preference) {
        if (preferences == null){
            preferences = new ArrayList<>();
        }
        preferences.add(preference);
    }

    public static void setUserGames(UserGame userGame) {
        if (userGames == null){
            userGames = new ArrayList<>();
        }
        userGames.add(userGame);
    }

    public static void setGames(Game game) {
        if (games == null){
            games = new ArrayList<>();
        }
        games.add(game);
    }
}
