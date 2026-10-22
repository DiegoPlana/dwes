package org.example.heroquesttracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Hero {
    @Id
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "Class" , nullable = true)
    private String heroClass;
    @Column(name = "lvl" , nullable = false)
    private int nivel;
    @Column(name = "xp"  , nullable = false)
    private float experience;

    @OneToMany(mappedBy = "hero")
    private List<Quest> quests;
}
