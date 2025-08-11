package com.example.petstore.model;

import java.time.LocalDate;

public class Fish extends Pet {
    private boolean isSaltWater;

    public Fish(String id, String name, LocalDate birthDate, boolean isSaltWater) {
        super(id, name, birthDate);
        this.isSaltWater = isSaltWater;
    }

    @Override
    public String speak() {
        return "blub blub";
    }

    @Override
    public String getType() {
        return PetType.FISH.name();
    }

    public boolean isSaltWater() {
        return isSaltWater;
    }

    public void setSaltWater(boolean saltWater) {
        isSaltWater = saltWater;
    }

}
