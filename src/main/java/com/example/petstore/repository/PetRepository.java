package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;

import java.util.Optional;

public class PetRepository {
    private final DynamoDbTable<Pet> petTable;

    public PetRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.petTable = enhancedClient.table("Pets", TableSchema.fromBean(Pet.class));
        petTable.createTable(); // Optional: create table if not exists
    }

    public void save(Pet pet) {
        petTable.putItem(pet);
    }

    public Optional<Pet> findById(String id) {
        Pet pet = petTable.getItem(Key.builder().partitionValue(id).build());
        return Optional.ofNullable(pet);
    }

    public void deleteById(String id) {
        petTable.deleteItem(Key.builder().partitionValue(id).build());
    }
}
