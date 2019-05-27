package com.sfe.superHero.controller;

import com.sfe.superHero.model.Mission;
import com.sfe.superHero.model.SuperHero;
import com.sfe.superHero.service.MissionService;
import com.sfe.superHero.service.SuperHeroService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperHeroControllerIntegrationTest {

    @Autowired
    MissionService missionService;

    @Autowired
    SuperHeroController superHeroController;

    @Autowired
    SuperHeroService superHeroService;

    @Test
    public  void contextLoad() {
        Assert.assertNotNull(this.superHeroController);
    }

    @Test
    public void testReadCreatDelete(){
        String superHeroName = "Wonder Woman";
        SuperHero superMan = new SuperHero();
        superMan.setSuperHeroName(superHeroName);

        SuperHero superHero = this.superHeroController.create(superMan);
        Iterable<SuperHero> heroes = this.superHeroController.findAll();
        Assertions.assertThat(heroes).first().hasFieldOrPropertyWithValue("superHeroName",superHeroName );

        this.superHeroController.delete(superHero.getId());
        Assertions.assertThat(this.superHeroController.findById(superHero.getId()).getBody()).isNull();
    }

    @Test
    public void testNewMission() throws Exception {
        String missionName = "Saved the princess";
        Mission savedThePrincess = new Mission();
        savedThePrincess.setMissionName(missionName);

        String superHeroName = "Flash";
        SuperHero superMan = new SuperHero();
        superMan.setSuperHeroName(superHeroName);

        superMan = this.superHeroService.save(superMan);
        savedThePrincess = this.missionService.save(savedThePrincess);

        superMan = this.superHeroController.newMission(superMan.getId(), savedThePrincess.getId()).getBody();
        Assertions.assertThat(superMan.getMissions()).first().hasFieldOrPropertyWithValue("missionName", missionName);
    }
}
