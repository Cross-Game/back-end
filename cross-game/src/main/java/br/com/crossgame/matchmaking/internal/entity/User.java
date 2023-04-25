package br.com.crossgame.matchmaking.internal.entity;

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
public class User implements Serializable {

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
    @Size(min = 12, message = "password must contain at least 12 charcters")
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Feedback> feedbacks;

    public User(Long id, String username, String email, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public void setFriends(Friend friend) {
        if(this.friends == null){
            this.friends = new ArrayList<>();
        }
        this.friends.add(friend);
    }

    public void setFeedbacks(Feedback feedback) {
        if (this.feedbacks == null){
            this.feedbacks = new ArrayList<>();
        }
        this.feedbacks.add(feedback);
    }
}
