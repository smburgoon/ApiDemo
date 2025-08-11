package com.example.petstore.controller;

import com.example.petstore.exception.PetNotFoundException;
import com.example.petstore.model.Pet;
import com.example.petstore.model.PetFactory;
import com.example.petstore.model.PetType;
import com.example.petstore.service.DefaultPetService;
import com.example.petstore.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;
    private final PetFactory petFactory;

    public PetController(DefaultPetService petService, PetFactory petFactory) {
        this.petService = petService;
        this.petFactory = petFactory;
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
    public ResponseEntity<Pet> createPet(@RequestBody PetRequest request) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("isTrained", request.getIsTrained());
        attributes.put("isIndoor", request.getIsIndoor());

        PetType type = PetType.valueOf(request.getType().toUpperCase());
        Pet pet = PetFactory.createPet(type, request.getId(), request.getName(), request.getBirthDate(), attributes);

        Pet saved = petService.addPet(pet);
        return ResponseEntity.ok(saved);
    }


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Pet addPet(@Valid @RequestBody Pet pet) {
//        return petService.addPet(pet);
//    }

}
