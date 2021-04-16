package com.desafioQuality.desafioQuality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private String hotelCode;
    private String name;
    private String destination;
    private String roomType;
    private double pricePerNight;
    private LocalDate availableFrom;
    private LocalDate availableTo;
    private String reserved;
}
