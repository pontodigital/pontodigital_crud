//package com.pontodigital.crud.repository;
//
//import com.github.leosant.mongoConfig.MongoConfig;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class MongoRepositories extends MongoConfig {
//
//    public MongoTemplate dateMongo;
//
//    public MongoRepositories() {
//        String connectioEnv = "leo:123@pontodigital.3ysb3.mongodb.net/pontodigital?retryWrites=true&w=majority";
//        mongoClientCluster(connectioEnv, "pontodigital");
//        dateMongo = mongoTemplate();
//    }
//
//    public static void mongoClientCluster(String mongoSrv, String database) {
//        MongoConfig.mongoClientCluster(mongoSrv, database);
//    }
//}
//
