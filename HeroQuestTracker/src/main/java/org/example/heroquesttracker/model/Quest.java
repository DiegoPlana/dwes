package org.example.heroquesttracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title",  nullable = false)
    private String title;
    @Column(name = "solved",  nullable = false)
    private Boolean solved;
    @Column(name = "xp",   nullable = false)
    private float experience;

    @ManyToOne
    @JoinColumn(name = "name")
    @ToString.Exclude
    private Hero hero;

}
