package com.sfe.superHero.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SuperHero {

    public SuperHero() {
    }

    public SuperHero(Long id, String firstName, String lastName, String superHeroName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.superHeroName = superHeroName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Firstname")
    private String firstName;

    @Column(name = "Lastname")
    private String lastName;

    @Column(name = "Superheroname", unique = true)
    private String superHeroName;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(
            name = "mission_super_hero",
            joinColumns = {@JoinColumn(name = "super_hero_id")},
            inverseJoinColumns = {@JoinColumn(name = "mission_id")}
    )
    @JsonIgnoreProperties("superHero")
    Set<Mission> missions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuperHeroName() {
        return superHeroName;
    }

    public void setSuperHeroName(String superHeroName) {
        this.superHeroName = superHeroName;
    }

    public Set<Mission> getMissions() {
        return missions;
    }

    public void setMissions(Set<Mission> missions) {
        this.missions = missions;
    }
}
