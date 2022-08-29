package com.digitaldot.app.config;

import com.digitaldot.utils.enviroment.EnvFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
public class EmployerDbConfig {

    private final EnvFile env;

    public EmployerDbConfig(EnvFile env) {
        this.env = env;
    }

    @Bean
    public DataSource employerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=empdb;encrypt=true;trustServerCertificate=true");
        dataSource.setUsername(env.get("DB.USERNAME"));
        dataSource.setPassword(env.get("DB.PASSWORD"));
        return dataSource;
    }

}
