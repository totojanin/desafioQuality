package com.desafioQuality.desafioQuality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingHotelRequestDTO {
    private String userName;
    private BookingHotelDTO booking;
}
