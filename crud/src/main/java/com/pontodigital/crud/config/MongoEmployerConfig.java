package com.pontodigital.crud.config;

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
@EnableMongoRepositories(basePackages = "com.pontodigital.crud.repository",
        mongoTemplateRef = "EmployerMongoTemplate")
public class MongoEmployerConfig {

    @Primary
    @ConfigurationProperties(prefix = "mongodb.ponto")
    @Bean
    public MongoProperties getEmployerProps() {
        return new MongoProperties();
    }

    @Bean(name = "EmployerMongoTemplate")
    public MongoTemplate employerMongoTemplate() {
        return new MongoTemplate(employerFactory(getEmployerProps()));
    }

    @Bean
    public MongoDatabaseFactory employerFactory(MongoProperties employerMongoProps) {
        return new SimpleMongoClientDatabaseFactory(employerMongoProps.getUri());
    }

}