package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ImportTxt;
import br.com.crossgame.matchmaking.internal.entity.GameRecommendation;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.GameRecommendationRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultImportTxt implements ImportTxt {

    private UserRepository userRepository;

    private GameRecommendationRepository gameRecommendationRepository;


    @Override
    public void execute(MultipartFile file, Long idUser) {
        String recordType ;
        int numberOfRecords = 0;

        List<GameRecommendation> gamesRecommendations = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                recordType  = line.substring(0,2);
                if (recordType.equals("00")){
                    log.info("Reading header....");
                    log.info("Type: "+line.substring(2,14));
                    log.info("Created date:"+line.substring(14,30));
                    log.info("User name:"+line.substring(30,48));
                } else if (recordType.equals("02")) {
                    log.info("Reading trailer...");
                    numberOfRecords = Integer.parseInt(line.substring(2,9));
                } else if (recordType.equals("01")) {
                    GameRecommendation gameRecommendation = new GameRecommendation();
                    log.info("Reading body values...");
                    gameRecommendation.setGameName(line.substring(2,16).trim());
                    log.info(gameRecommendation.getGameName());
                    gameRecommendation.setCompany(line.substring(16,36).trim());
                    log.info(gameRecommendation.getCompany());
                    gameRecommendation.setReason(line.substring(36,66).trim());
                    log.info(gameRecommendation.getReason());
                    gameRecommendation.setGenre(line.substring(66,76).trim());
                    log.info(gameRecommendation.getGenre());
                    User user = userRepository.findById(idUser).orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND!"));

                    gameRecommendation.setUser(user);
                    gamesRecommendations.add(gameRecommendation);
/*
                    user.getGameRecommendations().add(gameRecommendation);
*/
                    userRepository.save(user);
                    numberOfRecords++;
                }
                else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"THIS FILE HAVE INVALID FORMAT!");
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
