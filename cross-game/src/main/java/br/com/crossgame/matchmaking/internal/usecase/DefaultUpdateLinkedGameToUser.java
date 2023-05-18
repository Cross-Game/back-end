package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UserGameCreate;
import br.com.crossgame.matchmaking.api.model.UserGameResponse;
import br.com.crossgame.matchmaking.api.usecase.UpdateLinkedGameToUser;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import br.com.crossgame.matchmaking.internal.utils.UserGameResponseBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdateLinkedGameToUser implements UpdateLinkedGameToUser {

    private UserGameRepository userGameRepository;

    @Override
    public UserGameResponse execute(UserGameCreate userGameCreate) {
        UserGame userGame = this.userGameRepository.findById(userGameCreate.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Linked game not found with user id = " + userGameCreate.id()));
        userGame.setFavoriteGame(userGameCreate.isFavoriteGame());
        userGame.setUserNickname(userGameCreate.userNickname());
        userGame.setGamerId(userGameCreate.gamerId());
        userGame.setSkillLevel(userGameCreate.skillLevel());
        userGame.setGameFunction(userGameCreate.gameFunction());

        this.userGameRepository.save(userGame);
        return UserGameResponseBuildUtils.transform(userGame);
    }
}
