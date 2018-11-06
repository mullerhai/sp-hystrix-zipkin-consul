package com.tz.config;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongodbConfig {

    protected  final Logger logger=LoggerFactory.getLogger(MongodbConfig.class);





//    @Autowired
//    public MongoClient mongoClient ;//= new MongoClient("localhost", 27017);

//    @Bean
//    public MongoClient mongoClient(){
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        return mongoClient;
//    }


            //=new MongoTemplate(mongoClient,"modeldb_metadata");

//    @Autowired
//    public MongoTemplate mongoTemplate;


    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;


    public Metadata findOne(String modelid){

        logger.info("find data in mongodb");
        String name=mongoTemplate.getDb().getName();
        logger.warn(name);
        MongoDatabase db= mongoTemplate.getDb();
        logger.warn(db.toString());
        logger.warn( String.valueOf(mongoTemplate.getCollection("modeldb_metadata").countDocuments()));
        Query query=new Query(Criteria.where("modeldb_model_id").is(modelid));
        Metadata metadata=mongoTemplate.findOne(query,Metadata.class);
        logger.info("mongodb get meta "+metadata.toString());
        return metadata;
    }

    public List<Metadata> findlist(){
//        Query query=new Query( Criteria.);
//         MongoClient mongoClient = new MongoClient("localhost", 27017);
//        @SuppressWarnings("deprecation")
//        DB db = mongoClient.getDB("tiles");
        logger.info("find data in mongodb");
        String name=mongoTemplate.getDb().getName();
        logger.warn(mongoTemplate.getDb().getName());
        MongoDatabase db= mongoTemplate.getDb();
        logger.warn(db.toString());
        logger.warn( String.valueOf(mongoTemplate.getCollection("modeldb_metadata").countDocuments()));
        List<Metadata> melist=mongoTemplate.findAll(Metadata.class);

      melist.stream().forEach(el->System.out.println(el.toString()));
        return  melist;
    }
}
