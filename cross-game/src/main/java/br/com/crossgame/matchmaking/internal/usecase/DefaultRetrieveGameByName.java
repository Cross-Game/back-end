package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveGameByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class DefaultRetrieveGameByName implements RetrieveGameByName {
    private final Environment environment;

    @Autowired
    public DefaultRetrieveGameByName(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String execute(String gameName) throws IOException {
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

        return response.toString();
    }
}
