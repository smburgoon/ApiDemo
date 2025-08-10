package com.example.petstore.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("sql")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqlPetRepositoryHarnessTest {
}
