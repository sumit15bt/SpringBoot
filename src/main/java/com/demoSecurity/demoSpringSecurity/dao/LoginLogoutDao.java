package com.demoSecurity.demoSpringSecurity.dao;

import com.demoSecurity.demoSpringSecurity.dbBeans.ct_domain;
import com.demoSecurity.demoSpringSecurity.dbBeans.ct_user;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Log4j2
@Repository
public class LoginLogoutDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<ct_user> findUserByName(String name, Long domainId) {
        String query = "SELECT * FROM ct_user WHERE name=? AND domainid=?";
        Object[] parameters = new Object[]{name, domainId};
        RowMapper<ct_user> rowMapper = new BeanPropertyRowMapper<>(ct_user.class);
        return jdbcTemplate.query(query, parameters, rowMapper).stream()
                .findFirst();
    }

    public Optional<ct_domain> findDomainByName(String domainName) {
        String query = "SELECT * FROM ct_domain WHERE name=?";
        RowMapper<ct_domain> rowMapper = new BeanPropertyRowMapper<>(ct_domain.class);
        return jdbcTemplate.query(query, rowMapper, domainName).stream()
                .findFirst();
    }
}
