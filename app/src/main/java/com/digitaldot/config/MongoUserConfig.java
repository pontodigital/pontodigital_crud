//package com.digitaldot.config;
//
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//@Configuration
//@EnableMongoRepositories(basePackages = "com.digitaldot.repository.user",
//        mongoTemplateRef = "UserTemplate")
//public class MongoUserConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix = "mongodb.empdb")
//    public MongoProperties getUserProps() {
//        return new MongoProperties();
//    }
//
//    @Bean(name = "UserTemplate")
//    public MongoTemplate userTemplate() {
//        return new MongoTemplate(userFactory(getUserProps()));
//    }
//
//    @Bean
//    public MongoDatabaseFactory userFactory(MongoProperties userProps) {
//        return new SimpleMongoClientDatabaseFactory(userProps.getUri());
//    }
//
//}
