package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import com.example.petstore.model.PetFactory;
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
    private final PetFactory petFactory;

    public SqlPetRepository(JpaPetRepository jpaRepo, PetFactory petFactory) {
        this.jpaRepo = jpaRepo;
        this.petFactory = petFactory;
    }

    @Override
    public Pet save(Pet pet) {
        PetEntity entity = petFactory.toEntity(pet);
        PetEntity saved = jpaRepo.save(entity);
        return petFactory.fromEntity(saved);
    }

    @Override
    public Optional<Pet> findById(String id) {
        return jpaRepo.findById(id).map(petFactory::fromEntity);
    }

    @Override
    public List<Pet> findAll() {
        return jpaRepo.findAll().stream()
                .map(petFactory::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        jpaRepo.deleteById(id);
    }

}
