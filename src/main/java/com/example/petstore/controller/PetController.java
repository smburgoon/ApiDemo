package com.example.petstore.controller;

import com.example.petstore.exception.PetNotFoundException;
import com.example.petstore.model.DynamoDBPet;
import com.example.petstore.model.Pet;
import com.example.petstore.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

//    @GetMapping
//    public List<Pet> getAllPets() {
//        return petService.getAllPets();
//    }

    @GetMapping
    public List<DynamoDBPet> getAllPets() {
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

//    @GetMapping("/{id}")
//    public Pet getPetById(@PathVariable Long id) {
//        return petService.getPetById(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DynamoDBPet> getPet(@PathVariable String id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<Pet> getPet(@PathVariable String id) {
//        return petService.getPetById(id)
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));
//    }


//    @GetMapping("/{id}")
//    public Pet getPetById(@PathVariable Long id) {
//        return petService.getPetById(id)
//                .orElseThrow(() -> new PetNotFoundException("Pet not found"));
//    }

//    @PostMapping
//    public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet) {
//        Pet createdPet =  petService.addPet(pet);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DynamoDBPet addPet(@Valid @RequestBody Pet pet) {
        return petService.addPet(pet);
    }



}
