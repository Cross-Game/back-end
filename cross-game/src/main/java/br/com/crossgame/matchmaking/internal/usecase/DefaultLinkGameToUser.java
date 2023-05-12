package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameCreate;
import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.usecase.LinkGameToUser;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import br.com.crossgame.matchmaking.internal.utils.UserGameResponseBuildUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class DefaultLinkGameToUser implements LinkGameToUser {

    private RetrieveGameById retrieveGameById;
    private RetrieveUserById retrieveUserById;
    private UserGameRepository userGameRepository;

    @Override
    public UserGameResponse execute(UserGameCreate userGameCreate, Long gameId, Long userId) {
        User user = this.retrieveUserById.execute(userId);
        Game game = this.retrieveGameById.execute(gameId);
        if (this.gameAlreadyLinkedWithUser(user, game)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This game already linked with user");
        }
        UserGame userGame = new UserGame(0L,
                userGameCreate.isFavoriteGame(),
                userGameCreate.userNickname(),
                userGameCreate.gamerId(),
                userGameCreate.skillLevel(),
                game,
                user);

        UserGame userGameSaved = this.userGameRepository.save(userGame);
        return UserGameResponseBuildUtils.transform(userGameSaved);
    }

    private boolean gameAlreadyLinkedWithUser(User user, Game game){
        return user.getUserGames().stream()
                .anyMatch(userGame -> userGame.getGame().getGameName().equals(game.getGameName()));
    }
}
