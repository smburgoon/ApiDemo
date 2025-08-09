package com.example.petstore.api;

import java.util.Map;

public class PetTestData {

    public static Map<String, Object> validPet(String name, String type) {
        return Map.of("name", name, "type", type);
    }

    public static Map<String, Object> invalidPetMissingName() {
        return Map.of("type", "Dog");
    }

    public static Map<String, Object> updatedPet(String name, String type) {
        return Map.of("name", name, "type", type);
    }
}