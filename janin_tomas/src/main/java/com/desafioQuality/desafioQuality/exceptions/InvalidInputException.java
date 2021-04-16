package com.desafioQuality.desafioQuality.exceptions;

import lombok.Data;

@Data
public class InvalidInputException extends Exception {
    private final String title = "Invalid Input";

    public InvalidInputException(String message) {
        super(message);
    }
}
