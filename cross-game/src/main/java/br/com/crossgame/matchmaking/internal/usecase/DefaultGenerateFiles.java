package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.GenerateFiles;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.exception.ListIsEmptyExcepetion;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.ListaObj;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class DefaultGenerateFiles implements GenerateFiles {

    private UserRepository userRepository;

        @Override
        public void execute(Long userId, String archiveType) throws IOException {
            if (!(archiveType.equals("csv") || archiveType.equals("txt"))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de arquivo inválido!");
            }
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Usuário com o id %s não foi encontrado!", userId)));
            List<Friend> friends = user.getFriends();
            if (friends.isEmpty()) {
                throw new ListIsEmptyExcepetion("A lista de amigos está vazia");
            }
            ListaObj<Friend> friendsListaObj = new ListaObj<>(friends.size());
            friends.forEach(friends1 -> friendsListaObj.adiciona(friends1));

            String path = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "friend-list."
                    + archiveType;
            FileWriter writer = new FileWriter(path);
            writer.append("USERNAME");
            writer.append(";");
            writer.append("INICIO AMIZADE");
            writer.append(";");
            writer.append("FEEDBACK");
            writer.append(";");
            writer.append("SKILL");
            writer.append(";");
            writer.append("INICIO AMIZADE");
            writer.append(";");
            writer.append("COMPORTAMENTO");
            writer.append(";");
            writer.append("DATA FEEDBACK");
            writer.append(";");
            writer.append("\n");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (int i = 0; i < friendsListaObj.getNroElem(); i++) {
                Friend  friend = friendsListaObj.getElemento(i);
                User userFriend = userRepository.findByUsername(friend.getUsername()).get();
                List<Feedback> feedbackList = userFriend.getFeedbacks();
                Feedback feedback=  feedbackList.get(feedbackList.size()-1);
                try {
                    writer.append(friend.getUsername());
                    writer.append(";");
                    writer.append(friend.getFriendshipStartDate().format(formatter));
                    writer.append(";");
                    writer.append(feedback.getFeedbackText());
                    writer.append(";");
                    writer.append(feedback.getSkill().toString());
                    writer.append(";");
                    writer.append(feedback.getBehavior().toString());
                    writer.append(";");
                    writer.append(feedback.getFeedbackGivenDate().toString());
                    writer.append(";");
                    writer.append("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            writer.flush();
            writer.close();
        }

}
