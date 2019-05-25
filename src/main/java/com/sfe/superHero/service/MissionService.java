package com.sfe.superHero.service;

import com.sfe.superHero.model.Mission;
import org.springframework.data.repository.CrudRepository;

public interface MissionService extends CrudRepository<Mission, Long> {
}
