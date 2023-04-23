package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/feedbacks")
public interface FeedbackController {

    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    UserAndFeedback createFeedbackForAUser(@PathVariable Long userId, @RequestBody Feedback feedback);

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<Feedback> retrieveAllFeedbackByUserId(@PathVariable Long userId);

    @PutMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    UserAndFeedback updateUserFeedback(@PathVariable Long userId, @RequestBody Feedback feedback);

    @DeleteMapping(path = "/{userId}/{feedbackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserFeedback(@PathVariable Long userId, @PathVariable Long feedbackId);
}
