package br.com.crossgame.matchmaking.internal.entity;

import br.com.crossgame.matchmaking.api.observer.Observer;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User implements Serializable, Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @Size(min = 3, message = "username must contain at least 3 characters")
    private String username;

    @Column(name = "email")
    @Email(message = "must be an email")
    private String email;

    @Column(name = "password")
    @Size(min = 12, message = "password must contain at least 12 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_online")
    private boolean isOnline;

    @Nullable
    @JsonIgnore
    @Column(name = "profile_picture", length = 50 * 1024 * 1024)
    private byte[] profilePicture;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Friend> friends;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "preference_id")
    private Preference preferences;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Feedback> feedbacks;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Notification> notifies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<UserGame> userGames;

    public User(String username, String email, String password, boolean isOnline, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isOnline = isOnline;
        this.role = role;
    }


    public User(Long id, String username, String email, Role role) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
            this.isOnline = isOnline;
            this.role = role;
        }

        public void setFriends (Friend friend){
            if (this.friends == null) {
                this.friends = new ArrayList<>();
            }
            this.friends.add(friend);
        }

        public void setFeedbacks (Feedback feedback){
            if (this.feedbacks == null) {
                this.feedbacks = new ArrayList<>();
            }
            this.feedbacks.add(feedback);
        }

        public void setUserGames (UserGame usersGame){
            if (this.userGames == null) {
                this.userGames = new ArrayList<>();
            }
            this.userGames.add(usersGame);
        }

        public void setPreferences(Preference preference) {
            if (this.preferences == null) {
                this.preferences = new Preference();

            }
        }


    @Override
    public void update(Notification notification) {
        notifies.add(notification);
    }
}
