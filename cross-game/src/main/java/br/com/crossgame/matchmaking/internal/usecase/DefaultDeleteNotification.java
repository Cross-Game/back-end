package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.api.usecase.DeleteNotification;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationState;
import br.com.crossgame.matchmaking.internal.repository.NotificationRepository;
import br.com.crossgame.matchmaking.internal.utils.NotificationBuildUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DefaultDeleteNotification implements DeleteNotification {

    private NotificationRepository notificationRepository;

    @Override
    public NotificationResponse execute(Long id) {
        Notification notificationCannotBeFound = notificationRepository.findById(id).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification cannot be found"));

        notificationCannotBeFound.setNotificationState(NotificationState.CANCELLED);
        return NotificationBuildUtils.transform(this.notificationRepository.save(notificationCannotBeFound));

    }
}
