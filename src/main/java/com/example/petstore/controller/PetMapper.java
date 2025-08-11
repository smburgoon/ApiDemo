package com.example.petstore.controller;

import com.example.petstore.model.Cat;
import com.example.petstore.model.Dog;
import com.example.petstore.model.Pet;

import java.util.List;
import java.util.stream.Collectors;

public class PetMapper {

    public PetResponse toResponse(Pet pet) {
        PetResponse response = new PetResponse();
        response.setId(pet.getId());
        response.setName(pet.getName());
        response.setType(pet.getType());
        response.setBirthDate(pet.getBirthDate());

        if (pet instanceof Dog dog) {
            response.setIsTrained(dog.isTrained());
        } else if (pet instanceof Cat cat) {
            response.setIsIndoor(cat.isIndoor());
        }

        return response;
    }

    public List<PetResponse> toResponseList(List<Pet> pets) {
        return pets.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
