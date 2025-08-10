package com.example.petstore;

import com.example.petstore.repository.DynamoDBPetRepositoryHarnessTest;
import com.example.petstore.repository.SqlPetRepositoryHarnessTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        SqlPetRepositoryHarnessTest.class,
        DynamoDBPetRepositoryHarnessTest.class
})
public class PetRepositoryIntegrationSuite {
}
