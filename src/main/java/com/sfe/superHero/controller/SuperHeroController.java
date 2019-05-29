package com.sfe.superHero.controller;

import com.sfe.superHero.model.Mission;
import com.sfe.superHero.model.SuperHero;
import com.sfe.superHero.service.MissionService;
import com.sfe.superHero.service.SuperHeroMissionService;
import com.sfe.superHero.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.Optional;

@RestController
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    @Autowired
    private MissionService missionService;

    @Autowired
    private SuperHeroMissionService superHeroMissionService;

    @GetMapping("/SuperHero")
    public Iterable<SuperHero> findAll() {
        return this.superHeroService.findAll();
    }

    @GetMapping("/findSuper")
    public Iterable<SuperHero> findSuper() {
        return this.superHeroMissionService.findSupers();
    }

    @GetMapping("/SuperHero/{id}")
    public ResponseEntity<SuperHero> findById(@PathVariable Long id) {
        return this.superHeroService.findById(id).map(result -> new ResponseEntity(result, HttpStatus.OK)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findSuper/{id}")
    public ResponseEntity<SuperHero> findSuperHeroById(@PathVariable Long id) {
        return this.superHeroMissionService.findSuperById(id).map(result -> new ResponseEntity(result, HttpStatus.OK)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/SuperHero")
    public SuperHero create(@Valid @RequestBody SuperHero SuperHero) {
        return superHeroService.save(SuperHero);
    }

    @PutMapping("/SuperHero")
    public SuperHero update(@RequestBody SuperHero SuperHero) {
        return superHeroService.save(SuperHero);
    }

    @PutMapping("/SuperHero/{id}")
    public void delete(@PathVariable Long id) throws ValidationException {
        Optional<SuperHero> hero = this.superHeroMissionService.findSuperById(id);
        if (hero.isPresent()) {
            this.superHeroService.delete(hero.get());
        } else
            throw new ValidationException("Cannot find this super hero.");
    }

    @PutMapping("/SuperHero/mission")
    ResponseEntity<SuperHero> newMission(@RequestParam("superHeroId") Long superHeroId, @RequestParam("missionId") Long missionId) throws ValidationException {

        Optional<SuperHero> hero = this.superHeroMissionService.findSuperById(superHeroId);
        if (hero.isPresent()) {
            Optional<Mission> mission = this.missionService.findById(missionId);
            if (mission.isPresent() && !mission.get().getDeleted()) {
                hero.get().getMissions().add(mission.get());
                return new ResponseEntity(this.superHeroService.save(hero.get()), HttpStatus.OK);
            } else
                throw new ValidationException("Cannot add this mission.");
        } else
            throw new ValidationException("Cannot find this super hero.");
    }

    @DeleteMapping("/SuperHero/mission")
    ResponseEntity<SuperHero> abortMission(@RequestParam("superHeroId") Long superHeroId, @RequestParam("missionId") Long missionId) throws ValidationException {

        Optional<SuperHero> hero = this.superHeroMissionService.findSuperById(superHeroId);
        if (hero.isPresent()) {
            Optional<Mission> mission = this.missionService.findById(missionId);
            if (mission.isPresent()) {
                if (!mission.get().getCompleted()) {
                    hero.get().getMissions().remove(mission);
                    return new ResponseEntity(this.superHeroService.save(hero.get()), HttpStatus.OK);
                } else
                    throw new ValidationException("Mission already completed.");

            } else
                throw new ValidationException("Cannot find this mission.");
        } else
            throw new ValidationException("Cannot find this super hero.");
    }


}
