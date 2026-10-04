package daw2.dwes.morty1.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "episodes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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

    // Relación bidireccional con los personajes (Muchos a Muchos)
    @ManyToMany(mappedBy = "episodes")
    private List<Character> characters;
}

