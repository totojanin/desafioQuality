package com.desafioQuality.desafioQuality.dtos;

import lombok.Data;

@Data
public class PaymentMethodDTO {
    private String type;
    private String number;
    private int dues;
}
