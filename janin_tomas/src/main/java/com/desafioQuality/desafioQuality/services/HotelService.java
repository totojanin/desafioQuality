package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public interface HotelService {
    List<HotelDTO> findHotelsByFilters(String dateFrom, String dateTo, String destination) throws InvalidInputException, IOException, ParseException;
}
