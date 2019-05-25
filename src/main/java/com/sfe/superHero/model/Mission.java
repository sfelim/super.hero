package com.sfe.superHero.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MissionName")
    private String  missionName;

    @Column(name = "IsCompleted")
    private Boolean isCompleted;

    @Column(name = "Isdeleted")
    private Boolean isdeleted;

    @JsonBackReference
    @ManyToMany
    List<SuperHero> superHero;

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

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public List<SuperHero> getSuperHero() {
        return superHero;
    }

    public void setSuperHero(List<SuperHero> superHero) {
        this.superHero = superHero;
    }
}
