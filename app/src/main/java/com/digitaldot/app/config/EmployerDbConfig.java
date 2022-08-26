package com.digitaldot.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
public class EmployerDbConfig {

    @Bean
    public DataSource employerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=empdb;encrypt=true;trustServerCertificate=true");
        dataSource.setUsername("sa");
        dataSource.setPassword("Leo123456*");
        return dataSource;
    }

}
