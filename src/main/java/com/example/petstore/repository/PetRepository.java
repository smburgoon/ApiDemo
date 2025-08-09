package com.example.petstore.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.petstore.model.DynamoDBPet;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PetRepository {
    private static final Logger log = LoggerFactory.getLogger(PetRepository.class);

    private final DynamoDbTable<DynamoDBPet> petTable;

    public PetRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.petTable = enhancedClient.table("Pets", TableSchema.fromBean(DynamoDBPet.class));
        ensureTableExists(dynamoDbClient);

    }

    private void ensureTableExists(DynamoDbClient client) {
        try {
            client.describeTable(DescribeTableRequest.builder().tableName("Pets").build());
            log.info("Connected to existing DynamoDB table: Pets");
        } catch (ResourceNotFoundException e) {
            log.warn("Table 'Pets' not found. Creating new table...");
            petTable.createTable();
        }
    }


    public void save(DynamoDBPet pet) {
        pet.setId(UUID.randomUUID().toString());
        petTable.putItem(pet);
    }

    public Optional<DynamoDBPet> findById(String id) {
        DynamoDBPet pet = petTable.getItem(Key.builder().partitionValue(id).build());
        return Optional.ofNullable(pet);
    }

    public void deleteById(String id) {
        petTable.deleteItem(Key.builder().partitionValue(id).build());
    }

    public List<DynamoDBPet> findAll() {
        return petTable.scan().items().stream().toList();
    }

}
