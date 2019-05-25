package com.sfe.superHero.service;

import com.sfe.superHero.model.SuperHero;
import org.springframework.data.repository.CrudRepository;

public interface SuperHeroService extends CrudRepository<SuperHero, Long> {
}
