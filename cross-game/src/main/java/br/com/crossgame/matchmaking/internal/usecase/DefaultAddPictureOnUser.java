package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.AddPictureOnUser;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultAddPictureOnUser implements AddPictureOnUser {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(Long id, byte[] picture) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("User with id = %d not found", id));
        }
        userRepository.setProfilePicture(id, picture);
    }
}
