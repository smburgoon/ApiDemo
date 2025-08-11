package com.example.petstore.model;

import java.time.LocalDate;
import java.util.Map;

import static com.example.petstore.model.PetType.FISH;

public interface PetFactory {
    Pet fromEntity(Object source);
    Object toEntity(Pet pet, Class<?> targetType);

    public static Pet createPet(PetType type, String id, String name, LocalDate birthDate, Map<String, Object> attributes) {
        switch (type) {
            case DOG:
                return new Dog(id, name, birthDate, (boolean) attributes.getOrDefault("isTrained", false));
            case CAT:
                return new Cat(id, name, birthDate, (boolean) attributes.getOrDefault("isIndoor", true));
            case FISH:
                return new Fish(id, name, birthDate, (boolean) attributes.getOrDefault("isSaltWater", false));
            case FOX:
                return new Fox(id, name, birthDate, (boolean) attributes.getOrDefault("isIndoor", true));
            default:
                throw new IllegalArgumentException("Unsupported pet type: " + type);
        }
    }

}
