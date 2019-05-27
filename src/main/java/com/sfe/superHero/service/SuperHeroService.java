package com.sfe.superHero.service;

import com.sfe.superHero.model.SuperHero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SuperHeroService extends CrudRepository<SuperHero, Long>, SuperHeroMissionService {
    @Query(value = "select * from super_hero where Superheroname = :superHeroName", nativeQuery = true)
    SuperHero findBySuperHeroName(@Param("superHeroName") String superHeroName);
}
