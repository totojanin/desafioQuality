package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.BookingRequestDTO;
import com.desafioQuality.desafioQuality.dtos.BookingResponseDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;

import java.io.IOException;
import java.text.ParseException;

public interface BookingService {
    BookingResponseDTO reserve(BookingRequestDTO bookingRequest) throws InvalidInputException, IOException, ParseException;
}
