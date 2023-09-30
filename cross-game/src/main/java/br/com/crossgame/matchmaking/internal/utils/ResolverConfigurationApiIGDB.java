package br.com.crossgame.matchmaking.internal.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ResolverConfigurationApiIGDB {
    private static Environment environment;

    @Autowired
    private ResolverConfigurationApiIGDB(Environment environment) {
        this.environment = environment;
    }

    public static HttpEntity<String> execute() {
        String clientId = "tf19co76fr7q63zagpzoccxzpz8xxh";
        String bearerToken = Objects.requireNonNull(environment.getProperty("custom.api.bearer-token"));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(bearerToken);
        headers.set("Client-ID", clientId);

        return new HttpEntity<>(headers);
    }

}
