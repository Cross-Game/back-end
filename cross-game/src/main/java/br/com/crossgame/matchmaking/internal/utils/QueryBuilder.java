package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class QueryBuilder {

    private String query = "SELECT * FROM User u WHERE";

    private List<Preference> preferences;

    private List<UserGame> userGames;

    private List<Game> games;


    public String createQuery(){
        this.addUsergameAttributesOnQuery();
        this.addGameAttributesOnQuery();
        this.addPreferenceAttributesOnQuery();
        return this.query;
    }

    public void resetQuery(){
        this.query = "SELECT * FROM User u WHERE";
    }

    private boolean makeSureTheQueryIs24CharactersLong(){
        return this.query.length() == 26;
    }

    private void addAndClausuleOnQuery(){
        if (!this.makeSureTheQueryIs24CharactersLong()){
            this.query += " AND";
        }
    }

    private void addUsergameAttributesOnQuery(){
        if (!this.userGames.isEmpty()){
            for (UserGame userGame : this.userGames){
                if (!Objects.isNull(userGame.getSkillLevel())){
                    this.addAndClausuleOnQuery();
                    this.query += String.format(" u.userGames.skillLevel = '%s'",
                            userGame.getSkillLevel().name());
                }
                if (!Objects.isNull(userGame.getGameFunction())){
                    this.addAndClausuleOnQuery();
                    this.query += String.format(" u.userGames.gameFunction = '%s'",
                            userGame.getGameFunction().name());
                }
            }
        }
    }

    private void addGameAttributesOnQuery(){
        if (!this.games.isEmpty()){
            for (Game game : this.games){
                if (!Objects.isNull(game.getGameName())){
                    this.addAndClausuleOnQuery();
                    this.query += String.format(" u.userGames.game.gameName = '%s",
                            game.getGameName());
                }
                if (!Objects.isNull(game.getGameGenre())){
                    this.addAndClausuleOnQuery();
                    this.query += String.format(" u.userGames.game.gameGenre = '%s",
                            game.getGameGenre().name());
                }
            }
        }
    }

    private void addPreferenceAttributesOnQuery(){
        if(!this.preferences.isEmpty()){
            for(Preference preference : this.preferences){
                if (!Objects.isNull(preference.getFood())){
                    this.addAndClausuleOnQuery();
                    query += String.format(" u.preferences.food = '%s'",
                            preference.getFood());
                }
                if (!Objects.isNull(preference.getMovieGenre())){
                    this.addAndClausuleOnQuery();
                    query += String.format(" u.preferences.movieGenre = '%s'",
                            preference.getMovieGenre());
                }
                if (!Objects.isNull(preference.getSeriesGenre())){
                    this.addAndClausuleOnQuery();
                    query += String.format(" u.preferences.seriesGenre = '%s'",
                            preference.getSeriesGenre());
                }
                if (!Objects.isNull(preference.getGameGenre())){
                    this.addAndClausuleOnQuery();
                    query += String.format(" u.preferences.gameGenre = '%s'",
                            preference.getGameGenre());
                }
            }
        }
    }

    public String getQuery() {
        return this.query;
    }

    public void setPreferences(Preference preferences) {
        if (this.preferences == null){
            this.preferences = new ArrayList<>();
        }
        this.preferences.add(preferences);
    }

    public void setUserGames(UserGame userGames) {
        if (this.userGames == null){
            this.userGames = new ArrayList<>();
        }
        this.userGames.add(userGames);
    }

    public void setGames(Game games) {
        if (this.games == null){
            this.games = new ArrayList<>();
        }
        this.games.add(games);
    }
}
