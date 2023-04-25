package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.GenerateFiles;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.exception.ListIsEmptyExcepetion;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.ListaObj;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class DefaultGenerateFiles implements GenerateFiles {

    private UserRepository userRepository;
    @Override
    public void execute(Long userId,String archiveType) throws IOException {
      if (!(archiveType.equals("csv") || archiveType.equals("txt"))){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipo de arquivo inválido!");
      }
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Usuário com o id %s não foi encontrado!",userId)));
        List<Friend> friends = user.getFriends();
        if(friends.isEmpty()){
             throw new ListIsEmptyExcepetion("A lista de amigos está vazia");
        }
        ListaObj<Friend> friendsListaObj = new ListaObj<>(friends.size());
         friends.forEach(friends1 -> friendsListaObj.adiciona(friends1));

        try (FileWriter writer = new FileWriter("friend-list." + archiveType);){
            writer.append("USERNAME");
            writer.append(";");
            writer.append("INICIO AMIZADE");
            writer.append(";");
            writer.append("\n");

            for (int i = 0 ; i < friendsListaObj.getNroElem(); i++){
                Friend friend = friendsListaObj.getElemento(i);
                    writer.append(friend.getUsername());
                    writer.append(";");
                    writer.append(friend.getFriendshipStartDate().toString());
                    writer.append(";");
                    writer.append("\n");
            }
        }   catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
