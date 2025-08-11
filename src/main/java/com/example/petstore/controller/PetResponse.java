package com.example.petstore.controller;

import java.time.LocalDate;

public class PetResponse {
    private String id;
    private String name;
    private String type;
    private LocalDate birthDate;

    // Optional fields depending on type
    private Boolean isTrained;
    private Boolean isIndoor;

    // Constructors, getters, setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsTrained() {
        return isTrained;
    }

    public void setIsTrained(Boolean trained) {
        isTrained = trained;
    }

    public Boolean getIsIndoor() {
        return isIndoor;
    }

    public void setIsIndoor(Boolean indoor) {
        isIndoor = indoor;
    }
}
