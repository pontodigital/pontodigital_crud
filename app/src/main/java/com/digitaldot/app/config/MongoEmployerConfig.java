//package com.digitaldot.config;
//
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//@EnableMongoRepositories(basePackages = "com.digitaldot.repository",
//        mongoTemplateRef = "EmployerTemplate")
//public class MongoEmployerConfig {
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "mongodb.empdb")
//    public MongoProperties getEmployerProps() {
//        return new MongoProperties();
//    }
//
//    @Primary
//    @Bean(name = "EmployerTemplate")
//    public MongoTemplate employerTemplate() {
//        return new MongoTemplate(employerFactory(getEmployerProps()));
//    }
//
//    @Bean
//    @Primary
//    public MongoDatabaseFactory employerFactory(MongoProperties employerProps) {
//        return new SimpleMongoClientDatabaseFactory(employerProps.getUri());
//    }
//
//}