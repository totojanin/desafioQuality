package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface FlightService {
    List<FlightDTO> findFlightsByFilters(String dateFrom, String dateTo, String origin, String destination) throws InvalidInputException, IOException, ParseException;
    List<FlightDTO> findFlightByDateFrom(List<FlightDTO> flights, String dateFrom) throws InvalidInputException, ParseException;
    List<FlightDTO> findFlightByDateTo(List<FlightDTO> flights, String dateTo) throws InvalidInputException, ParseException;
    List<FlightDTO> findFlightByOrigin(List<FlightDTO> flights, String origin);
    List<FlightDTO> findFlightByDestination(List<FlightDTO> flights, String destination);
    List<FlightDTO> findFlightByFlightNumber(List<FlightDTO> flights, String flightNumber);
    List<FlightDTO> findFlightBySeatType(List<FlightDTO> flights, String seatType);
}
