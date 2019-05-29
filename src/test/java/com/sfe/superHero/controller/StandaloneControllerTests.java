package com.sfe.superHero.controller;


import com.sfe.superHero.model.SuperHero;
import com.sfe.superHero.service.SuperHeroService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SuperHeroController.class)
public class StandaloneControllerTests {

  @MockBean
  SuperHeroService superHeroService;

  @Autowired
  MockMvc mockMvc;

  @Test
  public void testCreateReadDelete() throws Exception {
    SuperHero hero = new SuperHero("Kent", "Klark","SuperMan");
    List<SuperHero> heroes = Arrays.asList(hero);

    Mockito.when(superHeroService.findAll()).thenReturn(heroes);

    mockMvc.perform(get("/SuperHero"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].firstName", Matchers.is("Kent")));
  }

}
