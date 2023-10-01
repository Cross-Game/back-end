package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.CreateGameApi;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.enums.TypeImage;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class DefaultCreateGameApi implements CreateGameApi {
    private final Environment environment;
    @Autowired
    private GenericGamesRepository repository;
    @Autowired
    private UserGameRepository userGameRepository;
    @Autowired
    private DefaultRetrieveGameByName retrieveGameByName;

    @Override
    public Optional<GenericGame> execute(String gameName) throws IOException {
        Optional<GenericGame> genericGame = Optional.empty();
        if (!gameName.isBlank()) {
            if (!gameAlreadyRegistered(gameName)) {
                genericGame = Optional.of(repository.save(retrieveGameByName.execute(gameName, TypeImage.cover_big)));
                return genericGame;
            }
        }
        return Optional.empty();
    }

    private boolean gameAlreadyRegistered(String name) {
        return !repository.findAll().stream().filter(game -> game.getGameName().contains(name)).collect(Collectors.toList()).isEmpty();

    }

}
