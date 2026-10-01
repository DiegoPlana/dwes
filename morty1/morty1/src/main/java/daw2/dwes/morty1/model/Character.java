package daw2.dwes.morty1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "characters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "character_name", nullable = false, length = 255)
    private String name;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "species", length = 100)
    private String species;

    // Renombrado de characterType -> type (la columna en BD no cambia)
    @Column(name = "character_type", length = 100)
    private String type;

    @Column(name = "gender", length = 50)
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    // Renombrado de imageUrl -> image (la columna en BD no cambia)
    @Column(name = "image_url", length = 255)
    private String image;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "character_episode",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    private Set<Episode> episodes = new HashSet<>();
}