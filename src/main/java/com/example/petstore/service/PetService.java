package com.example.petstore.service;

import com.example.petstore.exception.InvalidPetException;
import com.example.petstore.model.DynamoDBPet;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.*;
//import java.util.concurrent.atomic.AtomicLong;

@Service
public class PetService {
//    private final Map<Long, Pet> pets = new HashMap<>();
//    private final AtomicLong idCounter = new AtomicLong(1);

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

//    public List<Pet> getAllPets() {
//        return new ArrayList<>(pets.values());
//    }

    public List<DynamoDBPet> getAllPets() {
        return new ArrayList<>(petRepository.findAll());
    }

//    public Pet getPetById(Long id) {
//        return pets.get(id);
//    }

    public Optional<DynamoDBPet> getPetById(String id) {
        return petRepository.findById(id);
    }

//    public Optional<DynamoDBPet> updatePet(String id, Pet pet) {
//        if(petRepository.findById(id).isPresent()) {
//
//        }else {
//
//        }
//    }

    public void deletePet(String id) {
        petRepository.deleteById(id);
    }

    public DynamoDBPet addPet(Pet pet) {
        DynamoDBPet dynamoDBPet = validatePet(pet);
//        long newId = idCounter.getAndIncrement();
//        pet.setId(newId);
//        pets.put(pet.getId(), pet);
        String id = petRepository.save(dynamoDBPet);
        dynamoDBPet.setId(id);
        return dynamoDBPet;
    }

    private DynamoDBPet validatePet(Pet pet) {
        if (pet.getName() == null || pet.getName().isBlank()) {
            throw new InvalidPetException("Pet name must not be blank");
        }
        DynamoDBPet dynamoDBPet = new DynamoDBPet();
        dynamoDBPet.setName(pet.getName());
        dynamoDBPet.setType(pet.getType());
        dynamoDBPet.setAge(1);
        return dynamoDBPet;
    }

}
