package com.example.petstore.model;

import java.time.LocalDate;

public class Cat extends Pet {
    private boolean isIndoor;

    public Cat(String id, String name, LocalDate birthDate, boolean isIndoor) {
        super(id, name, birthDate);
        this.isIndoor = isIndoor;
    }

    @Override
    public String speak() {
        return "meow";
    }

    @Override
    public String getType() {
        return PetType.CAT.name();
    }

    public boolean isIndoor() {
        return isIndoor;
    }
}
