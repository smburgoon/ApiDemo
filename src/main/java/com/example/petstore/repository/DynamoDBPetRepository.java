package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.petstore.model.DynamoDBPet;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Profile("dynamo")
public class DynamoDBPetRepository implements PetRepository{
    private static final Logger log = LoggerFactory.getLogger(DynamoDBPetRepository.class);

    private final DynamoDbTable<Pet> petTable;

    public DynamoDBPetRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.petTable = enhancedClient.table("Pets", TableSchema.fromBean(Pet.class));
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

    @Override
    public Pet save(Pet pet) {
        String id = UUID.randomUUID().toString();
        pet.setId(id);
        petTable.putItem(pet);
        return pet;
    }

    @Override
    public Optional<Pet> findById(String id) {
        Pet pet = petTable.getItem(Key.builder().partitionValue(id).build());
        return Optional.ofNullable(pet);
    }

    public void deleteById(String id) {
        petTable.deleteItem(Key.builder().partitionValue(id).build());
    }

    public List<Pet> findAll() {
        return petTable.scan().items().stream().toList();
    }

}
