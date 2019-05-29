package com.sfe.superHero.dao.impl;

import com.sfe.superHero.dao.SuperHeroDao;
import com.sfe.superHero.model.Mission;
import com.sfe.superHero.model.SuperHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class SuperHeroDaoImpl implements SuperHeroDao {

    @Autowired
    DataSource dataSource;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Find all customers, thanks Java 8, you can create a custom RowMapper like this :
    public Iterable<SuperHero> findSupers() {

        Iterable<SuperHero> result = jdbcTemplate.query(
                "SELECT id, Superheroname, Firstname, Lastname FROM super_hero",
                (rs, rowNum) -> new SuperHero(rs.getLong("id"),
                        rs.getString("Firstname"), rs.getString("Lastname"), rs.getString("Superheroname"))
        );

        for (SuperHero hero : result)
            for (Mission mission : findMissions(hero))
                hero.getMissions().add(mission);

        return result;
    }

    @Override
    public Optional<SuperHero> findSuperById(Long id) {

        SuperHero result = jdbcTemplate.queryForObject("SELECT id, Superheroname, Firstname, Lastname FROM super_hero where id = ?", new Object[]{id}, (rs, rownum) -> new SuperHero(rs.getLong("id"),
                rs.getString("Firstname"), rs.getString("Lastname"), rs.getString("Superheroname")));

        return result != null ? Optional.of(result) : Optional.empty();
    }


    private Iterable<Mission> findMissions(SuperHero hero) {
        String query = "SELECT ID, IS_DELETED, IS_COMPLETED, MISSION_NAME FROM MISSION M " +
                "INNER JOIN MISSION_SUPER_HERO MS ON M.ID = MS.MISSION_ID AND MS.SUPER_HERO_ID = ? " +
                "WHERE IS_DELETED <> TRUE";

        Iterable<Mission> result = jdbcTemplate.query(
                query, new Object[]{hero.getId()},
                (rs, rowNum) -> new Mission(rs.getLong("ID"),
                        rs.getString("MISSION_NAME"), rs.getBoolean("IS_COMPLETED"), rs.getBoolean("IS_DELETED"))
        );
        return result;
    }
}
