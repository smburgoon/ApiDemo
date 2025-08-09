package com.example.petstore.service;

import com.example.petstore.exception.InvalidPetException;
import com.example.petstore.model.Pet;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PetService {
    private final Map<Long, Pet> pets = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Pet> getAllPets() {
        return new ArrayList<>(pets.values());
    }

    public Pet getPetById(Long id) {
        return pets.get(id);
    }

    public Pet addPet(Pet pet) {
        validatePet(pet);
        long newId = idCounter.getAndIncrement();
        pet.setId(newId);
        pets.put(pet.getId(), pet);
        return pet;
    }

    private void validatePet(Pet pet) {
        if (pet.getName() == null || pet.getName().isBlank()) {
            throw new InvalidPetException("Pet name must not be blank");
        }
        // Add other validations as needed
    }

}
