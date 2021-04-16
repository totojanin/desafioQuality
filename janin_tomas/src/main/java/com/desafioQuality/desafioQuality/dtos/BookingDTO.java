package com.desafioQuality.desafioQuality.dtos;

import lombok.Data;

@Data
public class BookingDTO extends BookingNoPaymentDTO {
    private PaymentMethodDTO paymentMethod;
}
