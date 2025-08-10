package com.example.petstore.controller;

import com.example.petstore.exception.PetNotFoundException;
import com.example.petstore.model.Pet;
import com.example.petstore.service.DefaultPetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final DefaultPetService petService;

    public PetController(DefaultPetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable String id) {
        petService.deletePet(id);
        return new ResponseEntity<>("Pet deleted successfully", HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<DynamoDBPet> updatePetById(@PathVariable String id, @Valid @RequestBody Pet pet) {
//        return petService.getPetById(id)
//                .map(petService.updatePet(id, pet))
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable String id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet addPet(@Valid @RequestBody Pet pet) {
        return petService.addPet(pet);
    }

}
