package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameCreate;
import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.usecase.UpdateLinkedGameToUser;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import br.com.crossgame.matchmaking.internal.utils.UserGameResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdateLinkedGameToUser implements UpdateLinkedGameToUser {

    private UserGameRepository userGameRepository;

    @Override
    public UserGameResponse execute(UserGameCreate userGameCreate) {
        UserGame userGame = this.userGameRepository.save(new UserGame(userGameCreate.id(),
                userGameCreate.isFavoriteGame(),
                userGameCreate.userNickname(),
                userGameCreate.gamerId(),
                userGameCreate.skillLevel()));
        return UserGameResponseBuildUtils.transform(userGame);
    }
}
