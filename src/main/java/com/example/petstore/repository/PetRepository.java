package com.example.petstore.repository;

import com.example.petstore.model.Pet;

import java.util.Optional;
import java.util.List;

public interface PetRepository {
    Pet save(Pet pet);
    Optional<Pet> findById(String id);
    List<Pet> findAll();
    void deleteById(String id);
}
