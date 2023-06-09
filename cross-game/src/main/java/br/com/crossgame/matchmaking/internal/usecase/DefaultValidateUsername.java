package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UsernameResponse;
import br.com.crossgame.matchmaking.api.usecase.ValidateUsername;
import br.com.crossgame.matchmaking.internal.utils.ConfigurationsVariables;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class DefaultValidateUsername implements ValidateUsername {

    @Override
    public ResponseEntity<UsernameResponse> execute(Long id, String username) {
        String url = String.format("https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s?api_key=%s",username, ConfigurationsVariables.API_KEY);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            log.info("Código de status: " + statusCode);
            String responseBody = response.body();
            log.info("Resposta: " + responseBody);
            if (statusCode == 404){
              throw  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("username %s not found", username));
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            UsernameResponse summoner = objectMapper.readValue(responseBody, UsernameResponse.class);


            return ResponseEntity.ok().body(summoner);

        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
