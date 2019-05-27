package com.sfe.superHero.service.impl;

import com.sfe.superHero.dao.SuperHeroDao;
import com.sfe.superHero.model.SuperHero;
import com.sfe.superHero.service.SuperHeroMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperHeroServiceImpl implements SuperHeroMissionService {
    @Autowired
    private SuperHeroDao superHeroDao;

    @Override
    public Iterable<SuperHero> findSupers() {
        return this.superHeroDao.findSupers();
    }

    @Override
    public Optional<SuperHero> findSuperById(Long id) {
        return this.superHeroDao.findSuperById(id);
    }
}
