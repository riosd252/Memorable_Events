package DavidRios.Memorable_Events.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private int capacity;
    @ManyToMany(mappedBy = "reservedEvents")
    @JsonIgnore
    private Set<User> participants = new HashSet<>();

    public Event(String title, String description, String date, String location, int capacity) {
        this.title = title;
        this.description = description;
        this.date = LocalDate.parse(date);
        this.location = location;
        this.capacity = capacity;

    }

    public void setParticipant(User user) {
        this.participants.add(user);
    }

    public void removeParticipant(User user) {this.participants.remove(user);}
}
