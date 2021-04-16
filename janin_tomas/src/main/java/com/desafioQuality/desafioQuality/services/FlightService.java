package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface FlightService {
    List<FlightDTO> findFlightsByFilters(String dateFrom, String dateTo, String origin, String destination) throws InvalidInputException, IOException, ParseException;
}
