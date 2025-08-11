package com.example.petstore.model;

import java.time.LocalDate;

public class Dog extends Pet {
    private boolean isTrained;

    public Dog(String id, String name, LocalDate birthDate, boolean isTrained) {
        super(id, name, birthDate);
        this.isTrained = isTrained;
    }

    @Override
    public String speak() {
        return "woof";
    }

    @Override
    public String getType() {
        return PetType.DOG.name();
    }

    public boolean isTrained() {
        return isTrained;
    }


    public void setIsTrained(boolean isTrained) {
        this.isTrained = isTrained;
    }
}
