package br.com.crossgame.matchmaking.internal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String description;
    private boolean isPrivate;
    private String tokenAccess;
    private boolean isTerminated;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_teamroom",
            joinColumns = @JoinColumn(name = "teamroom_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersInRoom;
    @ElementCollection
    private List<Long> usersHistoryId;
    private Long idUserAdmin;
}
