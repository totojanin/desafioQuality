package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.BookingFlightRequestDTO;
import com.desafioQuality.desafioQuality.dtos.BookingFlightResponseDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;

import java.io.IOException;
import java.text.ParseException;

public interface BookingFlightService {
    BookingFlightResponseDTO reserve(BookingFlightRequestDTO bookingRequest) throws InvalidInputException, IOException, ParseException;
}
