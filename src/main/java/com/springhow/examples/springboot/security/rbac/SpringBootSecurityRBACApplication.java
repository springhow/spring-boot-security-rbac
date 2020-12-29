package com.springhow.examples.springboot.security.rbac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("ALL")
@SpringBootApplication
public class SpringBootSecurityRBACApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootSecurityRBACApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityRBACApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        logger.info("{}", jdbcTemplate.query("select id,role_name from user_role", (rs, rowNum) -> rs.getString(1) + rs.getString(2)));
        logger.info("{}", jdbcTemplate.query("select id,privilege_name from user_privilege", (rs, rowNum) -> rs.getString(1) + rs.getString(2)));
        logger.info("{}", jdbcTemplate.query("select id,username,password,active from user_account", (rs, rowNum) -> rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4)));
        logger.info("{}", jdbcTemplate.query("select id,role_id,privilege_id from user_role_to_privilege", (rs, rowNum) -> rs.getString(1) + rs.getString(2) + rs.getString(3)));
        logger.info("{}", jdbcTemplate.query("select id,user_id,role_id from user_to_role", (rs, rowNum) -> rs.getString(1) + rs.getString(2) + rs.getString(3)));
    }
}
