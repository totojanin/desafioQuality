package com.desafioQuality.desafioQuality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingFlightRequestDTO {
    private String userName;
    private BookingFlightDTO flightReservation;
}