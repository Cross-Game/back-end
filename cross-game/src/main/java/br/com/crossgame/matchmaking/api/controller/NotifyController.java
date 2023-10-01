package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifies")
@CrossOrigin(maxAge = 3600)
public interface NotifyController {

    @PostMapping("/{idUser}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create notification", response = Notification.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Notification created")
    })
    NotificationResponse sendNotify(@PathVariable Long idUser, @RequestParam("type") NotificationType notification,
                                    @RequestParam("message") String message, @RequestParam("description") String description,
                                    @RequestParam("state") NotificationState state);

    @GetMapping("/{idUser}")
    List<NotificationResponse> retrieveNotify(@PathVariable Long idUser);

    @PatchMapping("/{id}")
    NotificationResponse negateNotify(@PathVariable Long id);

}
