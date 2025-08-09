package com.example.petstore.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetApiTest extends BaseApiTest {

    @Test
    void shouldCreatePetSuccessfully() {
        given()
                .contentType(ContentType.JSON)
                .body(PetTestData.validPet("Buddy", "Dog"))
                .when()
                .post("/api/pets")
                .then()
                .statusCode(201)
                .body("name", equalTo("Buddy"))
                .body("type", equalTo("Dog"));
    }

    @Test
    void shouldRetrievePetById() {
        Long petId = createPet("Whiskers", "Cat");

        given()
                .pathParam("id", petId)
                .when()
                .get("/api/pets/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Whiskers"))
                .body("type", equalTo("Cat"));
    }

//    @Test
//    void shouldUpdatePetSuccessfully() {
//        int petId = createPet("Max", "Dog");
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(PetTestData.updatedPet("Maximus", "Wolf"))
//                .pathParam("id", petId)
//                .when()
//                .put("/api/pets/{id}")
//                .then()
//                .statusCode(200)
//                .body("name", equalTo("Maximus"))
//                .body("type", equalTo("Wolf"));
//    }
//
//    @Test
//    void shouldDeletePetSuccessfully() {
//        int petId = createPet("Luna", "Cat");
//
//        given()
//                .pathParam("id", petId)
//                .when()
//                .delete("/api/pets/{id}")
//                .then()
//                .statusCode(204);
//
//        // Verify deletion
//        given()
//                .pathParam("id", petId)
//                .when()
//                .get("/api/pets/{id}")
//                .then()
//                .statusCode(404);
//    }
//
//    @Test
//    void shouldReturnAllPets() {
//        createPet("Charlie", "Dog");
//        createPet("Milo", "Cat");
//
//        when()
//                .get("/api/pets")
//                .then()
//                .statusCode(200)
//                .body("size()", greaterThanOrEqualTo(2));
//    }
//
//    @Test
//    void shouldFailToCreatePetWithMissingName() {
//        given()
//                .contentType(ContentType.JSON)
//                .body(PetTestData.invalidPetMissingName())
//                .when()
//                .post("/api/pets")
//                .then()
//                .statusCode(400);
//    }
//
//    @Test
//    void shouldReturn404ForNonexistentPet() {
//        given()
//                .pathParam("id", 99999)
//                .when()
//                .get("/api/pets/{id}")
//                .then()
//                .statusCode(404);
//    }

    // Helper method
    private Long createPet(String name, String type) {
        return given()
                .contentType(ContentType.JSON)
                .body(PetTestData.validPet(name, type))
                .when()
                .post("/api/pets")
                .then()
                .statusCode(201)
                .extract()
                .path("id");
    }
}