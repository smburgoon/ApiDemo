package com.example.petstore.config;

import com.example.petstore.repository.DynamoDBPetRepository;
import com.example.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;

import java.net.URI;

@Configuration
public class DynamoDbConfig {

    @Value("${DYNAMODB_ENDPOINT:http://localhost:8000}")
    private String dynamoDbEndpoint;

    @Bean
    public DynamoDbClient dynamoDbClient() {

       return DynamoDbClient.builder()
                .endpointOverride(URI.create(dynamoDbEndpoint))
                .httpClient(UrlConnectionHttpClient.builder().build())
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummyKey", "dummySecret")))
                .build();
    }

    @Bean
    public PetRepository petRepository(DynamoDbClient client) {
        return new DynamoDBPetRepository(client);
    }
}
