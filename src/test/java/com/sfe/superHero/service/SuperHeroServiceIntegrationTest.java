package com.sfe.superHero.service;

import com.sfe.superHero.model.Mission;
import com.sfe.superHero.model.SuperHero;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperHeroServiceIntegrationTest {
    @Autowired
    SuperHeroService superHeroService;

    @Autowired
    MissionService missionService;

    @Test
    public  void contextLoad() {
        Assert.assertNotNull(this.superHeroService);
    }

    @Test
    public void testCreate(){
        String superHeroName = "Super Man";
        SuperHero superMan = new SuperHero();
        superMan.setSuperHeroName(superHeroName);

        this.superHeroService.save(superMan);
        SuperHero hero = this.superHeroService.findBySuperHeroName(superHeroName);
        Assertions.assertThat(this.superHeroService.findBySuperHeroName(superHeroName)).hasFieldOrPropertyWithValue("superHeroName",superHeroName );

        this.superHeroService.delete(superMan);
        Assertions.assertThat(this.superHeroService.findBySuperHeroName(superHeroName)).isNull();
    }

}
