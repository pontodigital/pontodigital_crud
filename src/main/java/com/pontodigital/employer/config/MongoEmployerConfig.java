package com.pontodigital.employer.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.pontodigital.employer.repository.employer",
        mongoTemplateRef = "EmployerTemplate")
public class MongoEmployerConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "mongodb.empdb")
    public MongoProperties getEmployerProps() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "EmployerTemplate")
    public MongoTemplate employerTemplate() {
        return new MongoTemplate(employerFactory(getEmployerProps()));
    }

    @Bean
    @Primary
    public MongoDatabaseFactory employerFactory(MongoProperties employerProps) {
        return new SimpleMongoClientDatabaseFactory(employerProps.getUri());
    }

}