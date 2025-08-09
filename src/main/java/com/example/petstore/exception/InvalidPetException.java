package com.example.petstore.exception;

public class InvalidPetException extends ApiException {
    public InvalidPetException(String message) {
        super(message, ErrorCode.INVALID_PET);
    }
}
