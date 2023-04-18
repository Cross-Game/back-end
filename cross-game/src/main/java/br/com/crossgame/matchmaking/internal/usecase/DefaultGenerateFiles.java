package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.controller.FriendsController;
import br.com.crossgame.matchmaking.api.usecase.GenerateFiles;
import br.com.crossgame.matchmaking.internal.entity.Friends;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.FriendsRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultGenerateFiles implements GenerateFiles {

    private UserRepository userRepository;
    @Override
    public void execute(Long userId,String archiveType) throws IOException {
      if (!(archiveType.equals("csv") || archiveType.equals("txt"))){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipo de arquivo inválido!");
      }
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Usuário com o id %s não foi encontrado!",userId)));
        List<Friends> friends = user.getFriends();

        FileWriter writer = new FileWriter("friend-list."+archiveType);
        writer.append("USERNAME");
        writer.append(";");
        writer.append("INICIO AMIZADE");
        writer.append(";");
        writer.append("\n");
        friends.forEach(friend -> {
            try {
                writer.append(friend.getUsername());
                writer.append(";");
                writer.append(friend.getFriendshipStartDate().toString());
                writer.append(";");
                writer.append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.flush();
        writer.close();

    }
}
