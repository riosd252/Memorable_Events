package DavidRios.Memorable_Events.entities;

import DavidRios.Memorable_Events.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @ManyToMany
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> reservedEvents = new HashSet<>();

    public User (String username, String password, String fullName, String email, Roles role) {

        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;

    }

}
