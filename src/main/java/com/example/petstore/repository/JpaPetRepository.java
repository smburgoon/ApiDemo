package com.example.petstore.repository;

import com.example.petstore.model.sql.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPetRepository extends JpaRepository<PetEntity, String> {
}
