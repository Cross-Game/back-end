package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.UpdateLinkedGameToUser;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.UserGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DefaultUpdateLinkedGameToUser implements UpdateLinkedGameToUser {

    private UserGameRepository userGameRepository;

    @Override
    public UserGame execute(UserGame userGame) {
        return this.userGameRepository.save(userGame);
    }
}
