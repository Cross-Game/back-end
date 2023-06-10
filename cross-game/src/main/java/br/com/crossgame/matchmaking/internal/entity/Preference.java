package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.internal.entity.enums.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "preference")
@NoArgsConstructor
@Data
public class Preference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferences")
    private Preferences preferences;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_preferences",
                joinColumns = @JoinColumn(name = "preference_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Preference(Preferences preferences) {
        this.preferences = preferences;
    }
}
