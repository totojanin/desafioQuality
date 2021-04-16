package com.desafioQuality.desafioQuality.dtos;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private String username;
    private BookingDTO booking;
}
