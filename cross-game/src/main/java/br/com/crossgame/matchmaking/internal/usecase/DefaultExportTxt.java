package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ExportTxt;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.FriendRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class DefaultExportTxt implements ExportTxt {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendRepository friendRepository;

    @Override
    public void execute(Long idUser) {

    }

    private void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            log.error("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            saida.append(registro).append("\n");
            saida.close();
        } catch (IOException erro) {
            log.error("Erro ao gravar no arquivo");
        }
    }

    private void gravaArquivoTxt(String nomeArq, Long id) {
        nomeArq += ".txt";
        int contaRegistroDado = 0;
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id = %d not found", id)));


        String header = "00RELATORIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        header += user.getUsername();

        gravaRegistro(header, nomeArq);
        List<Friend> friendList = user.getFriends();
        if (!friendList.isEmpty()) {
            this.populateTxt(friendList, nomeArq);
            contaRegistroDado++;
        }

        String trailer = "02";
        trailer += String.format("%010d", contaRegistroDado);
        gravaRegistro(trailer, nomeArq);

    }

    private boolean validateFeedbackExist(User userFriend) {
        int lastFeedback = !userFriend.getFeedbacks().isEmpty() ? userFriend.getFeedbacks().size() - 1 : -1;
        return lastFeedback != -1 ;

    }

    private int lastIndex(User userFriend) {
        return this.validateFeedbackExist(userFriend) ? userFriend.getFeedbacks().size() - 1 : -1;
    }

    private int averageFeedback(List<Feedback> feedbacks) {
        int skillFeedback = feedbacks.stream().mapToInt(Feedback::getSkill).sum();
        int behaviorFeedback = feedbacks.stream().mapToInt(Feedback::getBehavior).sum();

        return (skillFeedback + behaviorFeedback) / feedbacks.size();
    }

    private void populateTxt(List<Friend> friendList, String nomeArq) {
        for (Friend friend : friendList) {
            User userFriend = userRepository.findById(friend.getId()).stream().findFirst().orElse(null);
            assert userFriend != null;
            String emailFriend = userFriend.getEmail();
            Feedback feedback;
            int avgFeedback;
            if (this.validateFeedbackExist(userFriend)) {
                feedback = userFriend.getFeedbacks().get(this.lastIndex(userFriend));
                avgFeedback = this.averageFeedback(userFriend.getFeedbacks());
            } else {
                feedback = new Feedback();
                avgFeedback = 0;
                feedback.setFeedbackText("SEM FEEDBACKS");
                feedback.setBehavior(0);
                feedback.setSkill(0);
            }

            String corpo = "01";
            corpo += String.format("%010d", friend.getId());
            corpo += String.format("%14.14s", friend.getUsername());
            corpo += String.format("%30.30s", emailFriend);
            corpo += String.format("%10s", friend.getFriendshipStartDate().toString());
            corpo += String.format("%-1d", avgFeedback);
            corpo += String.format("%-1d", feedback.getBehavior());
            corpo += String.format("%-1d", feedback.getSkill());
            gravaRegistro(corpo, nomeArq);
        }
    }
}
