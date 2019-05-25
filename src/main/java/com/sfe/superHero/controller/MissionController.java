package com.sfe.superHero.controller;

import com.sfe.superHero.model.Mission;
import com.sfe.superHero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MissionController {
    @Autowired
    private MissionService missionService;

    @GetMapping("/mission")
    public Iterable<Mission> findAll(){
        return this.missionService.findAll();
    }

    @GetMapping("/mission/{id}")
    public Mission findById(@PathVariable Long id){
        return this.missionService.findById(id).orElse(null);
    }

    @PostMapping("/mission")
    Mission create(@RequestBody Mission mission) {
        return missionService.save(mission);
    }

    @PutMapping("/mission")
    Mission update(@RequestBody Mission mission) {
        return missionService.save(mission);
    }

    @DeleteMapping("/mission/{id}")
    void delete(@PathVariable Long id) {
        missionService.deleteById(id);
    }

}
