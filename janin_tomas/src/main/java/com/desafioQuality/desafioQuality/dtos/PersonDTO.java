package com.desafioQuality.desafioQuality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO {
    private String dni;
    private String name;
    private String lastname;
    private String birthDate;
    private String mail;
}
