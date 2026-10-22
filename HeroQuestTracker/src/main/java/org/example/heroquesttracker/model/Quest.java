package org.example.heroquesttracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title",  nullable = false)
    private String title;
    @Column(name = "solved",  nullable = false)
    private Boolean Solved;
    @Column(name = "xp",   nullable = false)
    private float Experience;

    @ManyToOne
    @JoinColumn(name = "name")
    private Hero hero;

}
