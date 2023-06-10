package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ResponseNotify;
import br.com.crossgame.matchmaking.internal.entity.Notification;
import br.com.crossgame.matchmaking.internal.entity.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.NotificationRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultResponseNotify implements ResponseNotify {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public void execute(NotificationState response, Long userId, Long notificationId) {
        User user = userRepository.findById(userId).get();
        this.validateData(user);

        Notification notificationUser = user.getNotifies().stream()
                .filter(notification -> notification.getId().equals(notificationId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Notification not found"));

        if (response.equals(NotificationState.APPROVED)){
            notificationUser.setNotificationState(NotificationState.APPROVED);
        }
        if (response.equals(NotificationState.CANCELLED)){
            notificationUser.setNotificationState(NotificationState.CANCELLED);
        }
        if (response.equals(NotificationState.AWAITING)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This notification is on Awaiting");
        }
        notificationRepository.save(notificationUser);
    }

    private void validateData(User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found !");
        }
    }
}
