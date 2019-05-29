package com.sfe.superHero.controller;

import com.sfe.superHero.model.Mission;
import com.sfe.superHero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.Optional;

@RestController
public class MissionController {
    @Autowired
    private MissionService missionService;

    @GetMapping("/mission")
    public Iterable<Mission> findAll() {
        return this.missionService.findAll();
    }

    @GetMapping("/mission/{id}")
    public Mission findById(@PathVariable Long id) {
        return this.missionService.findById(id).orElse(null);
    }

    @PostMapping("/mission")
    Mission create(@Valid @RequestBody Mission mission) {
        return missionService.save(mission);
    }

    @PutMapping("/mission")
    Mission update(@RequestBody Mission mission) throws ValidationException {
        if (this.missionService.findById(mission.getId()).isPresent() && !mission.getDeleted().booleanValue()) {
            mission.setDeleted(true);
            return this.missionService.save(mission);
        } else throw new ValidationException("Cannot update this mission.");
    }

    @DeleteMapping("/mission/{id}")
    void delete(@PathVariable Long id) throws ValidationException {
        Optional<Mission> mission = this.missionService.findById(id);

        if (mission.isPresent() && !mission.get().getCompleted()) {
            mission.get().setDeleted(true);
            this.missionService.save(mission.get());
        } else throw new ValidationException("Cannot delete this mission.");
    }
}
