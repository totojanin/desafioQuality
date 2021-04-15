package com.desafioQuality.desafioQuality.exceptions;

import lombok.Data;

@Data
public class GeneralException extends Exception {
    private final String title = "Error";

    public GeneralException(String message) {
        super(message);
    }
}
