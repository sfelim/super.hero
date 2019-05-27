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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import javax.xml.bind.ValidationException;

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

    @Test
    public void testAbortMission() throws Exception {
        String missionName = "Saved the Princess Zelda";
        Mission savedThePrincess = new Mission();
        savedThePrincess.setMissionName(missionName);

        String superHeroName = "Link";
        SuperHero superMan = new SuperHero();
        superMan.setSuperHeroName(superHeroName);

        superMan = this.superHeroService.save(superMan);
        savedThePrincess = this.missionService.save(savedThePrincess);

        superMan = this.superHeroController.newMission(superMan.getId(), savedThePrincess.getId()).getBody();
        Assertions.assertThat(superMan.getMissions()).first().hasFieldOrPropertyWithValue("missionName", missionName);

        superMan = this.superHeroController.abortMission(superMan.getId(), savedThePrincess.getId()).getBody();
        Assertions.assertThat(superMan.getMissions()).doesNotContain(savedThePrincess);

    }

    @Test
    public void testAbortMissionCompleted() throws Exception {
        try {
            String missionName = "Saved the Princess Pitch";
            Mission savedThePrincess = new Mission();
            savedThePrincess.setMissionName(missionName);

            String superHeroName = "Mario";
            SuperHero superMario = new SuperHero();
            superMario.setSuperHeroName(superHeroName);

            superMario = this.superHeroService.save(superMario);
            savedThePrincess = this.missionService.save(savedThePrincess);
            savedThePrincess.setCompleted(true);
            savedThePrincess = this.missionService.save(savedThePrincess);

            superMario = this.superHeroController.newMission(superMario.getId(), savedThePrincess.getId()).getBody();
            Assertions.assertThat(superMario.getMissions()).first().hasFieldOrPropertyWithValue("missionName", missionName);

            superMario = this.superHeroController.abortMission(superMario.getId(), savedThePrincess.getId()).getBody();
            Assertions.assertThat(superMario.getMissions()).contains(savedThePrincess);
        } catch (ValidationException ex) {
            Assert.assertEquals("Mission already completed.", ex.getMessage());
        }

    }
}
