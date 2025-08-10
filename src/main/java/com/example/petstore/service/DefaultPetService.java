package com.example.petstore.service;

import com.example.petstore.exception.InvalidPetException;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.*;
//import java.util.concurrent.atomic.AtomicLong;

@Service
public class DefaultPetService implements PetService {
    // recalling the Atomic types may be beneficial in interview
//    private final Map<Long, Pet> pets = new HashMap<>();
//    private final AtomicLong idCounter = new AtomicLong(1);

    private final PetRepository petRepository;

    public DefaultPetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> getAllPets() {
        return new ArrayList<>(petRepository.findAll());
    }

//    public Pet getPetById(Long id) {
//        return pets.get(id);
//    }

    @Override
    public Optional<Pet> getPetById(String id) {
        return petRepository.findById(id);
    }

//    public Optional<DynamoDBPet> updatePet(String id, Pet pet) {
//        if(petRepository.findById(id).isPresent()) {
//
//        }else {
//
//        }
//    }

    @Override
    public void deletePet(String id) {
        petRepository.deleteById(id);
    }

    @Override
    public Pet addPet(Pet pet) {
        validatePet(pet);
        return petRepository.save(pet);
    }

    private void  validatePet(Pet pet) {
        if (pet.getName() == null || pet.getName().isBlank()) {
            throw new InvalidPetException("Pet name must not be blank");
        }
    }

}
