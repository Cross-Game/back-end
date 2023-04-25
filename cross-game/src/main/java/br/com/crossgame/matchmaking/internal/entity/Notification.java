package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.NotificationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private NotificationType notificationType;
    @NotBlank
    private String message;
    private String description;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notification(NotificationType notificationType, String message, String description, LocalDateTime date) {
        this.notificationType = notificationType;
        this.message = message;
        this.description = description;
        this.date = date;
    }

    public void notifyObservers(List<User> observers) {
        Notification notification = new Notification(notificationType, message, description, date);
        for (User observer : observers) {
            observer.update(notification);
        }
    }
}
