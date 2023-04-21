package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrievePicture;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultRetrievePicture implements RetrievePicture {

    private UserRepository userRepository;

    @Override
    public ResponseEntity<byte[]> execute(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException();
        }

        byte[] picture = userRepository.getProfilePicture(id);

        return ResponseEntity.status(HttpStatus.OK).header("content-disposition",
                "attachment; filename=\"foto-perfil.jpg\"").body(picture);
    }
}
