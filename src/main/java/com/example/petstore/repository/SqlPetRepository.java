package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import com.example.petstore.model.sql.PetEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("sql")
public class SqlPetRepository implements PetRepository {

    private final JpaPetRepository jpaRepo;

    public SqlPetRepository(JpaPetRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Pet save(Pet pet) {
        PetEntity entity = toEntity(pet);
        PetEntity saved = jpaRepo.save(entity);
        return toModel(saved);
    }

    @Override
    public Optional<Pet> findById(String id) {
        return jpaRepo.findById(id).map(this::toModel);
    }

    @Override
    public List<Pet> findAll() {
        return jpaRepo.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepo.deleteById(id);
    }

    private PetEntity toEntity(Pet pet) {
        PetEntity entity = new PetEntity();
        entity.setId(pet.getId());
        entity.setName(pet.getName());
        entity.setType(pet.getType());
        return entity;
    }

    private Pet toModel(PetEntity entity) {
        Pet pet = new Pet();
        pet.setId(entity.getId());
        pet.setName(entity.getName());
        pet.setType(entity.getType());
        return pet;
    }
}
