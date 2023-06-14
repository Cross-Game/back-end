package br.com.crossgame.matchmaking.internal.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String roomName;
    @Min(0)
    @Max(10)
    private int capacity;
    @NotBlank
    private String gameName;
    private String rankGame;
    private int levelGame;
    @Column(name = "descript")
    private String description;
    private boolean privateRoom;
    private String tokenAccess;
    @Column(name = "is_terminated")
    private boolean terminated;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_teamroom",
            joinColumns = @JoinColumn(name = "teamroom_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersInRoom;
    @ElementCollection
    private List<Long> usersHistoryId;
    private Long idUserAdmin;

}
