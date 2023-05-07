package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.LinkGameToUser;
import br.com.crossgame.matchmaking.api.usecase.RetrieveGameById;
import br.com.crossgame.matchmaking.api.usecase.RetrieveUserById;
import br.com.crossgame.matchmaking.internal.entity.Game;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public UserGame execute(UserGame userGame, Long gameId, Long userId) {
        User user = this.retrieveUserById.execute(userId);
        Game game = this.retrieveGameById.execute(gameId);

        userGame.setUser(user);
        userGame.setGame(game);

        return this.userGameRepository.save(userGame);
    }
}
