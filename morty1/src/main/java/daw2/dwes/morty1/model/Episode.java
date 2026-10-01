package daw2.dwes.morty1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "episodes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "episode_name", nullable = false, length = 255)
    private String name;

    @Column(name = "air_date")
    private LocalDate airDate;

    @Column(name = "episode_code", length = 20)
    private String episodeCode;

    //mappedBy apunta al campo "episodes" definido en Character
    @ManyToMany(mappedBy = "episodes", fetch = FetchType.LAZY)
    private Set<Character> characters = new HashSet<>();
}