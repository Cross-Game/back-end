package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.api.model.NotificationResponse;
import br.com.crossgame.matchmaking.internal.entity.Notification;

public class NotificationBuildUtils {

    public static NotificationResponse transform(Notification notification){
        return new NotificationResponse(notification.getId(),notification.getNotificationType()
                , notification.getMessage(), notification.getDescription(), notification.getDate());
    }
}
