package com.example.petstore.model;

import java.time.LocalDate;

public class Fox extends Pet {
    private boolean isIndoor;

    public Fox(String id, String name, LocalDate birthDate, boolean isIndoor) {
        super(id, name, birthDate);
        this.isIndoor = isIndoor;
    }

    @Override
    public String speak() {
        return "geting-ding-ding-ding wa-pow-pow-pow-pow";
    }

    @Override
    public String getType() {
        return PetType.FOX.name();
    }

    public boolean isIndoor() {
        return isIndoor;
    }
}
