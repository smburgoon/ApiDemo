package com.example.petstore.config;

import com.example.petstore.repository.PetRepository;
import com.example.petstore.repository.SqlPetRepository;
import com.example.petstore.repository.DynamoDBPetRepository;
import com.example.petstore.repository.JpaPetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class PetRepositoryConfig {
    private static final Logger log = LoggerFactory.getLogger(PetRepositoryConfig.class);
    private final PetFactory petFactory;

    @Autowired
    private Environment env;

    public PetRepositoryConfig(PetFactory petFactory) {
        this.petFactory = petFactory;
    }


    @Bean
    @Primary
    public PetRepository petRepository(
            @Autowired(required = false) JpaPetRepository jpaRepo,
            @Autowired(required = false) DynamoDbClient dynamoClient
    ) {
        String profile = String.join(",", env.getActiveProfiles());
        log.info("Active profile: {}", profile);

        if (profile.contains("sql")) {
            return new SqlPetRepository(jpaRepo, petFactory);
        } else if (profile.contains("dynamo")) {
            return new DynamoDBPetRepository(dynamoClient);
        } else {
            throw new IllegalStateException("No valid profile active: expected 'sql' or 'dynamo'");
        }

    }
}