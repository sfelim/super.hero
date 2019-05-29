package com.sfe.superHero.service;

import com.sfe.superHero.model.SuperHero;

import java.util.Optional;

public interface SuperHeroMissionService {
    Iterable<SuperHero> findSupers();

    Optional<SuperHero> findSuperById(Long id);
}
