package com.omc.sensors_processing.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration{

	@Value("${spring.data.mongodb.database}")
    private String databaseName;
	
	@Override
	protected String getDatabaseName() {
		return databaseName;
	}
}
