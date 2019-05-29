package com.sfe.superHero.dao;

import com.sfe.superHero.model.SuperHero;

import java.util.Optional;

public interface SuperHeroDao {
    Iterable<SuperHero> findSupers();

    Optional<SuperHero> findSuperById(Long id);
}
