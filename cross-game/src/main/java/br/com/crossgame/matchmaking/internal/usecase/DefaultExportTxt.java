package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.UsernameResponse;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<Resource> execute(Long idUser) {

        String username = userRepository.findById(idUser).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id = %d not found", idUser))).getUsername();

        String nameArchive = idUser + "-" + username;
        byte[] encodedBytes;
        try {
            encodedBytes = Files.readAllBytes(this.recordTxt(nameArchive, idUser).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InputStream inputStream = new ByteArrayInputStream( new String(encodedBytes, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8));

        Resource resource = new InputStreamResource(inputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", nameArchive+".txt");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);


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
        File file = null;
        try {
            file = File.createTempFile(nameArchive,".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            String favoriteGame = null;
            String userNickname = null;
            UsernameResponse usernameResponse = null;
            if (userFriend.getUserGames().stream().filter(game -> game.isFavoriteGame()).toList().isEmpty()){
                favoriteGame = "UNKNOW";
            }
            else {
                favoriteGame=   userFriend.getUserGames().stream().filter(game -> game.isFavoriteGame()).findFirst()
                        .get().getGame().getGameName();
                String finalFavoriteGame1 = favoriteGame;
                userNickname=  userFriend.getUserGames().stream().filter(game -> game.getGame().getGameName().equals(finalFavoriteGame1))
                        .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getUserNickname();
              usernameResponse =  validateUsername.execute(userFriend.getId(), userNickname, favoriteGame).getBody();
            }


            String corpo = "01";

            corpo += String.format("%010d", friend.getId());
            corpo += String.format("%-14.14s", friend.getUsername());
            corpo += String.format("%-30.30s", emailFriend);
            corpo += String.format("%10s", friend.getFriendshipStartDate().toString());
            corpo += String.format("%-1d", avgFeedback);
            corpo += String.format("%-1d", feedback.getBehavior());
            corpo += String.format("%-1d", feedback.getSkill());
            corpo += String.format("%20.20s", favoriteGame);

                if (Objects.isNull(usernameResponse)){
                    corpo += String.format("%-5d",0);
                    corpo += String.format("%10.10s", "UNRANKED");
                }
                else {
                    corpo += String.format("%-5d", this.gameLevel(usernameResponse).get());
                    corpo += String.format("%10.10s", this.rank(usernameResponse).get());
                }


            countValues++;
            recordData(corpo, file);
        }
    }

    private Optional<Integer> gameLevel(UsernameResponse body) {

        if (!Objects.isNull(body)) {
            if(Objects.isNull(body.summonerLevel())){
                return Optional.of(0);
            }
            return Optional.of(body.summonerLevel());
        }
       return Optional.empty();
    }

    private Optional<String> rank(UsernameResponse body) {


        if (!Objects.isNull(body)) {
            if (Objects.isNull(body.tier())){
                return Optional.of("UNRANKED");
            }
            return Optional.of(body.tier());
        }
        return Optional.of("0");
    }
}
