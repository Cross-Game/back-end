package br.com.crossgame.matchmaking.internal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "friends")
@NoArgsConstructor
@Data
public class Friend implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "friendship_start_date")
    private LocalDate friendshipStartDate;

    public Friend(String username, LocalDate friendshipStartDate) {
        this.username = username;
        this.friendshipStartDate = friendshipStartDate;
    }
}
