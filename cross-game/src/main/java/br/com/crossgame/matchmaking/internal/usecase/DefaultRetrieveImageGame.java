package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveImageGame;
import br.com.crossgame.matchmaking.internal.entity.ImageGame;
import br.com.crossgame.matchmaking.internal.entity.enums.TypeImage;
import br.com.crossgame.matchmaking.internal.utils.ResolverConfigurationApiIGDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Service
@Slf4j
public class DefaultRetrieveImageGame implements RetrieveImageGame {
    private final RestTemplate restTemplate = new RestTemplate(); // fixme definir um bean para o restTemplate

    @Override
    public ImageGame execute(Integer coverId, TypeImage typeImage) {
        typeImage = Objects.isNull(typeImage) ? TypeImage.logo_med : typeImage;

        ResponseEntity<ImageGame[]> exchange = restTemplate.exchange(
                "https://api.igdb.com/v4/covers/" + coverId + "?fields=*"
                , HttpMethod.GET
                , ResolverConfigurationApiIGDB.execute()
                , ImageGame[].class);

        ImageGame imageGame =
                Arrays.stream(Objects.requireNonNull(exchange.getBody())).toList().stream().findFirst().orElseThrow(RuntimeException::new);

        String urlImage = "https://images.igdb.com/igdb/image/upload/t_" + typeImage + "/" + imageGame.getImageId() + ".jpg";

        imageGame.setLink(urlImage);
        imageGame.setTypeImage(typeImage);

        return imageGame;
    }
}
