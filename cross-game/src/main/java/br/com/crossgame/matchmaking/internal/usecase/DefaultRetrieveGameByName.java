package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveGameByName;
import br.com.crossgame.matchmaking.api.usecase.RetrieveImageGame;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.ImageGame;
import br.com.crossgame.matchmaking.internal.entity.TypeImage;
import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import br.com.crossgame.matchmaking.internal.utils.ResolverConfigurationApiIGDB;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
@Data
@Transactional
public class DefaultRetrieveGameByName implements RetrieveGameByName {

    private final RestTemplate restTemplate = new RestTemplate();  // fixme definir um bean para o restTemplate

    @Autowired
    private RetrieveImageGame retrieveImageGame;

    @Override
    public GenericGame execute(String gameName, TypeImage typeImage) throws IOException {

        ResponseEntity<GenericGame[]> exchange = restTemplate.exchange(
                "https://api.igdb.com/v4/games/?search=" + gameName + "&fields=name,summary,cover,platforms&limit=1"
                , HttpMethod.GET
                , ResolverConfigurationApiIGDB.execute()
                , GenericGame[].class);

        GenericGame genericGame = Arrays.stream(Objects.requireNonNull(exchange.getBody())).findFirst().orElseThrow(RuntimeException::new);

        ImageGame imageGame = retrieveImageGame.execute(genericGame.getCoverId(), typeImage);
        genericGame.setImageGame(imageGame);

        return genericGame;
    }


    private void enrichPlatforms(List<GameplayPlatformType> platforms){

    }
}
