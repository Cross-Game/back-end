package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.api.observer.Observable;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationState;
import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Notification extends Observable implements Serializable {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private NotificationType notificationType;
    @NotBlank
    private String message;
    private String description;
    private LocalDateTime date;

    private NotificationState notificationState;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    public Notification( NotificationType notificationType, String message, String description, LocalDateTime date, User user,NotificationState state) {
        this.notificationType = notificationType;
        this.message = message;
        this.description = description;
        this.date = date;
        this.user = user;
        this.notificationState = state;
    }


}
