package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.internal.entity.GenericGame;
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

    private static List<GenericGame> games;

    private static int whereCount = 0;


    public static String createQuery(){
        resetQuery();
        addJoinClausuleOnQuery();
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

    private static void resetQuery(){
        whereCount = 0;
        query = "SELECT u FROM User u";
    }

    private static boolean verifyIfExisteWhereToAddAndClausule(){
        return whereCount > 1;
    }

    private static void addAndClausuleOnQuery(){
        if (query.contains("WHERE")){
            query += " AND";
        }
    }

    private static void addJoinClausuleOnQuery(){
        if (!userGames.isEmpty()){
            for (UserGame userGame : userGames) {
                if (!Objects.isNull(userGame.getSkillLevel()) || !Objects.isNull(userGame.getGameFunction())) {
                    query += " JOIN u.userGames ug";
                }
            }
        }
        if (!games.isEmpty()){
            for (GenericGame game : games) {
                if (!Objects.isNull(game.getGameName()) || !Objects.isNull(game.getGameGenres())) {
                    if (query.contains(" JOIN u.userGames ug")){
                        query += " JOIN ug.genericGame g";
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

   private static void addUsergameAttributesOnQuery(){
        if (!userGames.isEmpty()){
            for (UserGame userGame : userGames){
                if (!Objects.isNull(userGame.getSkillLevel())){
                        addAndClausuleOnQuery();
                    if (!verifyIfExisteWhereToAddAndClausule()){
                        whereCount++;
                        if (!verifyIfExisteWhereToAddAndClausule()){
                            query += " WHERE";
                        }
                    }
                    query += String.format(" ug.skillLevel = '%s'",
                            userGame.getSkillLevel().name());
                }
            }
        }
    }

    private static void addGameAttributesOnQuery(){
        if (!games.isEmpty()){
            for (GenericGame game : games){
                if (!Objects.isNull(game.getGameName())){
                        addAndClausuleOnQuery();
                    if (!verifyIfExisteWhereToAddAndClausule()){
                        whereCount++;
                        if (!verifyIfExisteWhereToAddAndClausule()){
                            query += " WHERE";
                        }
                    }
                    query += String.format(" g.gameName = '%s'",
                            game.getGameName());
                }
            }
        }
    }

    private static void addPreferenceAttributesOnQuery(){
        if(!preferences.isEmpty()){
            for(Preference preference : preferences){
                if (!Objects.isNull(preference.getPreferences())){
                        addAndClausuleOnQuery();
                    if (!verifyIfExisteWhereToAddAndClausule()){
                        whereCount++;
                        if (!verifyIfExisteWhereToAddAndClausule()){
                            query += " WHERE";
                        }
                    }
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

    public static void setGames(GenericGame game) {
        if (games == null){
            games = new ArrayList<>();
        }
        games.add(game);
    }
}
