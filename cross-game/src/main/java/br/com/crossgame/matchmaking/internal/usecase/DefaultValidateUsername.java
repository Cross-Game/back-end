package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ValidateUsername;
import br.com.crossgame.matchmaking.internal.utils.Configurations;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j

public class DefaultValidateUsername implements ValidateUsername {

    @Override
    public void execute(Long id, String username) {
        String url = String.format("https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s?api_key=%s", Configurations.getApiKey());
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            // Enviar a requisição HTTP e receber a resposta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o código de status da resposta
            int statusCode = response.statusCode();
            log.info("Código de status: " + statusCode);
            String responseBody = response.body();
            log.info("Resposta: " + responseBody);
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
