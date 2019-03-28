package ru.chuikov.server.config;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class MongoDBConfig extends AbstractMongoConfiguration {

    {
    /*
    @Value("${mongo.dbname}")
    private String databaseName;

    @Value("${mongo.username}")
    private String username;

    @Value("${mongo.password}")
    private String password;

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private int port;
    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host,port);
    }

    @Bean
    public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory().getDb();
        return GridFSBuckets.create(db);
    }

    @Bean
    public Mongo mongo() {
        return new MongoClient(singletonList(
                new ServerAddress(host, port)),
                singletonList(
                        MongoCredential.createCredential(
                                username,
                                getDatabaseName(),
                                password.toCharArray())));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
    */
    }

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host,port);
    }

    @Bean
    public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory().getDb();
        return GridFSBuckets.create(db);
    }
}
