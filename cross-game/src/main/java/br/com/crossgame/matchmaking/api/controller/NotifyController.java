package br.com.crossgame.matchmaking.api.controller;

import br.com.crossgame.matchmaking.internal.entity.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifies")
public interface NotifyController {

    @PostMapping("/{idUser}/{idRoom}")
    void sendNotify(@PathVariable Long idUser,@PathVariable Long idRoom,@RequestBody Notification notification);

    @GetMapping("/{idUser}")
    ResponseEntity<List<Notification>> retrieveNotify(Long idUser);

}
