package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameCreate;
import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.usecase.LinkGameToUser;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.GenericGame;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.GenericGamesRepository;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import br.com.crossgame.matchmaking.internal.utils.UserGameResponseBuildUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class DefaultLinkGameToUser implements LinkGameToUser {

    private RetrieveGameById retrieveGameById;
    private RetrieveUserById retrieveUserById;
    private UserGameRepository userGameRepository;
    private GenericGamesRepository genericGamesRepository;

    @Override
    public UserGameResponse execute(UserGameCreate userGameCreate, Long gameId, Long userId) {
        User user = this.retrieveUserById.execute(userId);

        List<GenericGame> genericGames = retrieveListOfGenericGames(userGameCreate.GenericGamersIds());

        UserGame userGame = new UserGame();
        userGame.setUser(user);
        userGame.setGenericGames(genericGames);
        userGame.setUserNickname(userGameCreate.userNickname());
        userGame.setSkillLevel(userGameCreate.skillLevel());
        userGame.setGamerId(userGameCreate.gamerId());

        UserGame userGameSaved = this.userGameRepository.save(userGame);
        return UserGameResponseBuildUtils.transform(userGameSaved);
    }

    private List<GenericGame> retrieveListOfGenericGames(List<Long> idsGames) {
        List<Long> collect = idsGames.stream().filter(id -> genericGamesRepository.existsById(id)).collect(Collectors.toList());

        if (collect.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Houve um erro ao vincular um jogo ao usuário");
        }

        return genericGamesRepository.findAllById(collect);
    }
}
