package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;

import java.time.LocalDateTime;

public record NotificationResponse(Long id, NotificationType type, String message, String description,
                                   LocalDateTime date, NotificationState state) {
}
