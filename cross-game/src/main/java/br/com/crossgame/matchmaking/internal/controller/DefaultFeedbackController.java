package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.FeedbackController;
import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.api.usecase.*;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(FeedbackController.class)
public class DefaultFeedbackController implements FeedbackController{

    private CreateFeedbackForAUser createFeedbackForAUser;
    private RetrieveAllFeedbackByUserId retrieveAllFeedbackByUserId;
    private UpdateUserFeedback updateUserFeedback;
    private DeleteUserFeedback deleteUserFeedback;
    private ExportTxt exportTxt;

    @Override
    public UserAndFeedback createFeedbackForAUser(Long userId, Feedback feedback) {
        return this.createFeedbackForAUser.execute(userId, feedback);
    }

    @Override
    public List<Feedback> retrieveAllFeedbackByUserId(Long userId) {
        return this.retrieveAllFeedbackByUserId.execute(userId);
    }

    @Override
    public UserAndFeedback updateUserFeedback(Long userId, Feedback feedback) {
        return this.updateUserFeedback.execute(userId, feedback);
    }

    @Override
    public void deleteUserFeedback(Long userId, Long feedbackId) {
        this.deleteUserFeedback.execute(userId, feedbackId);
    }

    @Override
    public ResponseEntity<Resource> retrieveFeedbackTxt(Long userId) {
    return this.exportTxt.execute(userId);
    }
    public ResponseEntity<Resource> downloadFile() {
        // Simule a obtenção dos dados para o arquivo TXT
        String fileContent = "Conteúdo do arquivo TXT";

        // Converta o conteúdo em um stream de entrada
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));

        // Crie um objeto de recurso de stream de entrada para o arquivo
        Resource resource = new InputStreamResource(inputStream);

        // Defina os cabeçalhos da resposta para indicar que é um arquivo TXT
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "data.txt");

        // Retorne a resposta com o arquivo e os cabeçalhos adequados
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
