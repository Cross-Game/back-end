package br.com.crossgame.matchmaking.internal.controller;

import br.com.crossgame.matchmaking.api.controller.NotifyController;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.usecase.DefaultSendNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class DefaultNotifyController implements NotifyController {
    @Autowired
    private DefaultSendNotify defaultSendNotify;
    @Override
    public void sendNotify(Long idUser, Long idRoom,Notification notification) {
        defaultSendNotify.execute(idUser,idRoom,notification);
    }

    @Override
    public ResponseEntity<List<Notification>> retrieveNotify(Long idUser) {
        return null;
    }
}
