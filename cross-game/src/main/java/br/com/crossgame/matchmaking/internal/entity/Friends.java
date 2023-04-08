package br.com.crossgame.matchmaking.internal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Friends implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "friendship_start_date")
    private LocalDate friendshipStartDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Friends(String username, LocalDate friendshipStartDate) {
        this.username = username;
        this.friendshipStartDate = friendshipStartDate;
    }
}
