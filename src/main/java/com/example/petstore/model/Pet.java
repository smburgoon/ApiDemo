package com.example.petstore.model;

import jakarta.validation.constraints.NotBlank;

public class Pet {
    private String id;
    @NotBlank
    private String name;
    private String type;

    public Pet() {}

    public Pet(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
