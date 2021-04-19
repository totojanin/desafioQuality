package com.desafioQuality.desafioQuality.dtos;

import lombok.Data;

@Data
public class BookingResponseDTO {
    private String userName;
    private double amount;
    private double interest;
    private double total;
    private BookingNoPaymentDTO booking;
    private StatusCodeDTO statusCode;
}
