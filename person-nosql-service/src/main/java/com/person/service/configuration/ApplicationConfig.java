package com.person.service.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
class ApplicationConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    public String mongoUri;

    @Override
    protected void configureClientSettings(final MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString(mongoUri));
    }

    @Override
    protected String getDatabaseName() {
        return "persons";
    }
}