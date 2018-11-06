package com.tz.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
public class DBconfig {

    protected  final Logger logger=LoggerFactory.getLogger(DBconfig.class);

    @Bean
    public MongoTemplate mongoTemplate(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoTemplate  mongoTemplate=new MongoTemplate(mongoClient,"modeldb_metadata");
        logger.info("创建mongodb实力");
        MongoDatabase db= mongoTemplate.getDb();
        logger.warn(db.toString());
        List<Metadata> melist=mongoTemplate.findAll(Metadata.class);

        melist.stream().forEach(el->System.out.println(el.toString()));
        return  mongoTemplate;

    }
}




