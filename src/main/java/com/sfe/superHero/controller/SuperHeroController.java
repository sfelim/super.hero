package com.sfe.superHero.controller;

import com.sfe.superHero.model.SuperHero;
import com.sfe.superHero.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    @GetMapping("/SuperHero")
    public Iterable<SuperHero> findAll(){
        return this.superHeroService.findAll();
    }

    @GetMapping("/SuperHero/{id}")
    public SuperHero findById(@PathVariable Long id){
        return this.superHeroService.findById(id).orElse(null);
    }

    @PostMapping("/SuperHero")
    SuperHero create(@RequestBody SuperHero SuperHero) {
        return superHeroService.save(SuperHero);
    }

    @PutMapping("/SuperHero")
    SuperHero update(@RequestBody SuperHero SuperHero) {
        return superHeroService.save(SuperHero);
    }

    @DeleteMapping("/SuperHero/{id}")
    void delete(@PathVariable Long id) {
        superHeroService.deleteById(id);
    }
    
}
