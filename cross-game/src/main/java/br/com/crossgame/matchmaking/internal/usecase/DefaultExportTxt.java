package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ExportTxt;
import br.com.crossgame.matchmaking.api.usecase.ValidateUsername;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.entity.UserGame;
import br.com.crossgame.matchmaking.internal.repository.FriendRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DefaultExportTxt implements ExportTxt {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private ValidateUsername validateUsername;

    private int countValues = 0;

    @Override
    public File execute(Long idUser) {

        String username = userRepository.findById(idUser).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id = %d not found", idUser))).getUsername();

        String nameArchive = System.getProperty("user.home") + File.separator + "Downloads" + File.separator
                + idUser + "-" + username
                + ".txt";
        return this.recordTxt(nameArchive, idUser);

    }

    private void recordData(String value, File file) {
        BufferedWriter output = null;

        try {
            output = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException erro) {
            log.error("Error with open archive");
            System.exit(1);
        }

        try {
            output.append(value).append("\n");
            output.close();
        } catch (IOException error) {
            log.error("Error with record archive:" + error);
        }
    }

    private File recordTxt(String nameArchive, Long id) {
        File file = new File(nameArchive);
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id = %d not found", id)));


        String header = "00RELATORIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        header += user.getUsername();

        recordData(header, file);
        List<Friend> friendList = user.getFriends();
        if (!friendList.isEmpty()) {
            this.populateTxt(friendList, file);
        }

        String trailer = "02";
        trailer += String.format("%010d", countValues);
        recordData(trailer, file);

        return file;
    }

    private boolean validateFeedbackExist(User userFriend) {
        int lastFeedback = !userFriend.getFeedbacks().isEmpty() ? userFriend.getFeedbacks().size() - 1 : -1;
        return lastFeedback != -1;

    }

    private int lastIndex(User userFriend) {
        return this.validateFeedbackExist(userFriend) ? userFriend.getFeedbacks().size() - 1 : -1;
    }

    private int averageFeedback(List<Feedback> feedbacks) {
        int skillFeedback = feedbacks.stream().mapToInt(Feedback::getSkill).sum() / feedbacks.size();
        int behaviorFeedback = feedbacks.stream().mapToInt(Feedback::getBehavior).sum() / feedbacks.size();

        return (skillFeedback + behaviorFeedback) / 2;
    }

    private void populateTxt(List<Friend> friendList, File file) {
        for (Friend friend : friendList) {
            User userFriend = userRepository.findByUsername(friend.getUsername()).stream().findFirst().orElse(null);
            if (Objects.isNull(userFriend)) {
                return;
            }
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
            String favotoriteGame = userFriend.getUserGames().stream().filter(game -> game.isFavoriteGame())
                    .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getGame().getGameName();
            String corpo = "01";

            corpo += String.format("%010d", friend.getId());
            corpo += String.format("%-14.14s", friend.getUsername());
            corpo += String.format("%-30.30s", emailFriend);
            corpo += String.format("%10s", friend.getFriendshipStartDate().toString());
            corpo += String.format("%-1d", avgFeedback);
            corpo += String.format("%-1d", feedback.getBehavior());
            corpo += String.format("%-1d", feedback.getSkill());
            corpo += String.format("%20.20s", favotoriteGame);
            corpo += String.format("%-5d", this.gameLevel(userFriend, favotoriteGame));

            countValues++;
            recordData(corpo, file);
        }
    }

    private int gameLevel(User user, String gameName) {
        String userNickname = user.getUserGames().stream().filter(UserGame::isFavoriteGame)
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getUserNickname();


        if (validateUsername.execute(user.getId(), userNickname, gameName).getBody() != null) {
            return validateUsername.execute(user.getId(), userNickname, gameName).getBody().summonerLevel();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not have a gameLevel");
    }

    private String rank(User user, String gameName) {
        String userNickname = user.getUserGames().stream().filter(game -> game.isFavoriteGame())
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getUserNickname();


        if (validateUsername.execute(user.getId(), userNickname, gameName).getBody() != null) {
            validateUsername.execute(user.getId(), userNickname, gameName).getBody().tier();
        }
        return null;
    }
}
