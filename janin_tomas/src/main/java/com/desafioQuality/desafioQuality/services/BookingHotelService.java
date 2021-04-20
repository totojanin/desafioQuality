package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.BookingHotelRequestDTO;
import com.desafioQuality.desafioQuality.dtos.BookingHotelResponseDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;

import java.io.IOException;
import java.text.ParseException;

public interface BookingHotelService {
    BookingHotelResponseDTO reserve(BookingHotelRequestDTO bookingRequest) throws InvalidInputException, IOException, ParseException;
}
