package com.example.petstore.model;

import com.example.petstore.model.dynamo.DynamoDBPet;
import com.example.petstore.model.sql.PetEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DefaultPetFactory implements PetFactory {

    @Override
    public Pet fromEntity(Object source) {
        if (source instanceof PetEntity entity) {
            return new Pet(entity.getId(), entity.getName(), entity.getType(), entity.getBirthDate());
        } else if (source instanceof DynamoDBPet record) {
            return new Pet(record.getId(), record.getName(), record.getType(),
                    LocalDate.parse(record.getBirthDate()));
        }
        throw new IllegalArgumentException("Unsupported source type: " + source.getClass());
    }

    @Override
    public Object toEntity(Pet pet, Class<?> targetType) {
        if (targetType.equals(PetEntity.class)) {
            return new PetEntity(pet.getId(), pet.getName(), pet.getType(), pet.getBirthDate());
        } else if (targetType.equals(DynamoDBPet.class)) {
            DynamoDBPet record = new DynamoDBPet();
            record.setId(pet.getId());
            record.setName(pet.getName());
            record.setType(pet.getType());
            record.setBirthDate(pet.getBirthDate().toString());
            return record;
        }
        throw new IllegalArgumentException("Unsupported target type: " + targetType);
    }

}