package com.example.petstore.model;

import com.example.petstore.model.sql.PetEntity;
import org.springframework.stereotype.Component;

@Component
public class PetFactory {

    public Pet fromEntity(PetEntity entity) {
        return switch (entity.getType()) {
            case "Dog" -> new Dog(entity.getId(), entity.getName(), entity.getBirthDate(), entity.getIsTrained());
            case "Cat" -> new Cat(entity.getId(), entity.getName(), entity.getBirthDate(), entity.getIsIndoor());
            default -> throw new IllegalArgumentException("Unknown pet type: " + entity.getType());
        };
    }

    public PetEntity toEntity(Pet pet) {
        PetEntity entity = new PetEntity();
        entity.setId(pet.getId());
        entity.setName(pet.getName());
        entity.setBirthDate(pet.getBirthDate());
        entity.setType(pet.getType());

        if (pet instanceof Dog dog) {
            entity.setIsTrained(dog.isTrained());
        } else if (pet instanceof Cat cat) {
            entity.setIsIndoor(cat.isIndoor());
        }

        return entity;
    }
}