package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.CreateGameApi;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameByName;
import br.com.crossgame.matchmaking.api.usecase.RetrieveImageGame;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.ImageGame;
import br.com.crossgame.matchmaking.internal.entity.enums.GameGenre;
import br.com.crossgame.matchmaking.internal.entity.enums.GameplayPlatformType;
import br.com.crossgame.matchmaking.internal.entity.enums.TypeImage;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import br.com.crossgame.matchmaking.internal.utils.ResolverConfigurationApiIGDB;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
@Data
@Transactional
public class DefaultRetrieveGameByName implements RetrieveGameByName {

    private final RestTemplate restTemplate = new RestTemplate();  // fixme definir um bean para o restTemplate

    @Autowired
    private RetrieveImageGame retrieveImageGame;

    @Autowired
    private CreateGameApi createGameApi;

    @Autowired
    private GenericGamesRepository genericGamesRepository;

    @Override
    public GenericGame
    execute(String gameName, TypeImage typeImage) throws IOException {
        ResponseEntity<GenericGame[]> exchange = restTemplate.exchange(
                "https://api.igdb.com/v4/games/?search=" + gameName + "&fields=name,summary,cover,platforms,genres"
                , HttpMethod.GET
                , ResolverConfigurationApiIGDB.execute()
                , GenericGame[].class);

        GenericGame genericGame = Arrays.stream(Objects.requireNonNull(exchange.getBody())).findFirst().orElseThrow(RuntimeException::new);
        ImageGame imageGame = retrieveImageGame.execute(genericGame.getCoverId(), typeImage);
        genericGame.setImageGame(imageGame);
        genericGame.setPlatformsType(GameplayPlatformType.mapIdsToPlatforms(genericGame.getPlatformsId()));
        genericGame.setGameGenres(GameGenre.mapIdsToGenres(genericGame.getGenreId()));

        return genericGame;
    }


}
