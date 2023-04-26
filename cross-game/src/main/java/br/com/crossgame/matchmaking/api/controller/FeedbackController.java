package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.UserAndFeedback;
import br.com.crossgame.matchmaking.internal.entity.Feedback;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/feedbacks")
@Api(tags = "Feedbacks end-points")
public interface FeedbackController {

    @PostMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Feedback created"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    UserAndFeedback createFeedbackForAUser(@PathVariable Long userId, @RequestBody Feedback feedback);

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Feedbacks listed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 409, message = "You cannot add a feedback for yourself"),
    })
    List<Feedback> retrieveAllFeedbackByUserId(@PathVariable Long userId);

    @PutMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Feedback updated"),
            @ApiResponse(code = 404, message = "Feedback id not found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    UserAndFeedback updateUserFeedback(@PathVariable Long userId, @RequestBody Feedback feedback);

    @DeleteMapping(path = "/{userId}/{feedbackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Feedback deleted"),
            @ApiResponse(code = 404, message = "Feedback not found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    void deleteUserFeedback(@PathVariable Long userId, @PathVariable Long feedbackId);
}
