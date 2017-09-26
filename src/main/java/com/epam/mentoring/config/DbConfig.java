package com.epam.mentoring.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.epam.mentoring.model")
@PropertySource("classpath:application.yml")
public class DbConfig extends AbstractMongoConfiguration {

    private Environment environment;

    public DbConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("spring.data.mongodb.database");
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }
}
