package com.sfe.superHero.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Mission {

    public Mission() {
    }

    public Mission(Long id, String missionName, Boolean isCompleted, Boolean IsDeleted) {
        this.id = id;
        this.missionName = missionName;
        this.isCompleted = isCompleted;
        this.IsDeleted = IsDeleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MissionName")
    private String  missionName;

    @Column(name = "IsCompleted")
    private Boolean isCompleted = false;

    @Column(name = "IsDeleted")
    private Boolean IsDeleted = false;

    @JsonIgnoreProperties("missions")
    @ManyToMany(mappedBy = "missions", fetch = FetchType.LAZY)
    Set<SuperHero> superHero = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }

    public Set<SuperHero> getSuperHero() {
        return superHero;
    }

    public void setSuperHero(Set<SuperHero> superHero) {
        this.superHero = superHero;
    }
}
