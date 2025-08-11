package com.example.petstore.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public abstract class Pet {
    private String id;
    private String name;
    protected LocalDate birthDate;

    public Pet() {}

    public Pet(String id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public abstract String speak();

    public abstract String getType();

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
