package com.desafioQuality.desafioQuality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCodeDTO {
    private int code;
    private String message;
}
