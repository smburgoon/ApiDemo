package com.example.petstore.service;

import com.example.petstore.model.Pet;

import java.util.Optional;
import java.util.List;

public interface PetService {
    Pet addPet(Pet pet);
    Optional<Pet> getPetById(String id);
    List<Pet> getAllPets();
    void deletePet(String id);
}
