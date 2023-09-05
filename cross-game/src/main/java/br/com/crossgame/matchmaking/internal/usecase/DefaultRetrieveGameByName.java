package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveGameByName;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@Data
@Transactional
public class DefaultRetrieveGameByName implements RetrieveGameByName {
    private final Environment environment;
    @Autowired
    private GenericGamesRepository repository;
    @Autowired
    public DefaultRetrieveGameByName(Environment environment) {
        this.environment = environment;
    }

    @Override
    public GenericGame execute(String gameName) throws IOException {
        if (gameAlreadyRegistered(gameName)){
            return retrieveIfExist(gameName).orElseThrow();
        }
         GenericGame genericGame = new GenericGame();
        ObjectMapper responseBody = new ObjectMapper();
        String clientId = "tf19co76fr7q63zagpzoccxzpz8xxh";
        String bearerToken = environment.getProperty("custom.api.bearer-token");

        URL url = new URL("https://api.igdb.com/v4/games/?search=" + gameName + "&fields=name,summary,cover,platforms");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
        connection.setRequestProperty("Client-ID", clientId);
        connection.setRequestProperty("Content-Type", "application/json"); // Adicionar o header para application/json

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        JsonNode jsonNode = responseBody.readTree(response.toString());
        genericGame.setGameName(jsonNode.get(0).get("name").asText());
        genericGame.setPlatform(jsonNode.get(0).get("platforms").get(0).asText());
        genericGame.setUrlImage(jsonNode.get(0).get("cover").asText());
       GenericGame game = repository.save(genericGame);

        return game;
    }

    private Optional<GenericGame> retrieveIfExist(String name){
    return repository.findByGameNameContains(name);
    }
    private boolean gameAlreadyRegistered(String name){
        return repository.existsByGameName(name);

        }



}
