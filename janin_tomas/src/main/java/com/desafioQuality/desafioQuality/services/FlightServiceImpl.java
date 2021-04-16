package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
    private final String DATE_FORMAT = "dd/MM/yyyy";

    private FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<FlightDTO> findFlightsByFilters(String dateFrom, String dateTo, String origin, String destination) throws InvalidInputException, IOException, ParseException {
        List<FlightDTO> flights = flightRepository.findFlights(DATE_FORMAT);

        validateInput(dateFrom, dateTo, origin, destination, flights);

        flights = findFlightByDateFrom(flights, dateFrom);
        flights = findFlightByDateTo(flights, dateTo);
        flights = findFlightByOrigin(flights, origin);
        flights = findFlightByDestination(flights, destination);

        return flights;
    }

    private List<FlightDTO> findFlightByDateFrom(List<FlightDTO> flights, String dateFromStr) throws InvalidInputException, ParseException {
        LocalDate dateFrom;

        if (dateFromStr == null)
            return flights;
        else {
            dateFrom = DateUtils.parse(DATE_FORMAT, dateFromStr);
        }

        List<FlightDTO> response = flights.stream()
                .filter(h -> h.getDateFrom().toEpochDay() <= dateFrom.toEpochDay() && h.getDateTo().toEpochDay() >= dateFrom.toEpochDay())
                .collect(Collectors.toList());

        if (!response.isEmpty())
            return response;
        else
            throw new InvalidInputException("There are no flights with the given input");
    }

    private List<FlightDTO> findFlightByDateTo(List<FlightDTO> flights, String dateToStr) throws InvalidInputException, ParseException {
        LocalDate dateTo;

        if (dateToStr == null)
            return flights;
        else {
            dateTo = DateUtils.parse(DATE_FORMAT, dateToStr);
        }

        List<FlightDTO> response = flights.stream()
                .filter(h -> h.getDateFrom().toEpochDay() <= dateTo.toEpochDay() && h.getDateTo().toEpochDay() >= dateTo.toEpochDay())
                .collect(Collectors.toList());

        if (!response.isEmpty())
            return response;
        else
            throw new InvalidInputException("There are no flights with the given input");
    }

    private List<FlightDTO> findFlightByOrigin(List<FlightDTO> flights, String origin) {
        if (origin == null)
            return flights;

        List<FlightDTO> response = flights.stream()
                .filter(h -> h.getOrigin().equalsIgnoreCase(origin))
                .collect(Collectors.toList());

        return response;
    }

    private List<FlightDTO> findFlightByDestination(List<FlightDTO> flights, String destination) {
        if (destination == null)
            return flights;

        List<FlightDTO> response = flights.stream()
                .filter(h -> h.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());

        return response;
    }

    private void validateInput(String dateFrom, String dateTo, String origin, String destination, List<FlightDTO> flights) throws InvalidInputException, ParseException {
        if (dateFrom != null && !DateUtils.isValid(DATE_FORMAT, dateFrom))
            throw new InvalidInputException("The Date From format must be dd/mm/aaaa");

        if (dateTo != null && !DateUtils.isValid(DATE_FORMAT, dateTo))
            throw new InvalidInputException("The Date To format must be dd/mm/aaaa");

        if (dateFrom != null && dateTo != null && DateUtils.parse(DATE_FORMAT, dateFrom).toEpochDay() > DateUtils.parse(DATE_FORMAT, dateTo).toEpochDay())
            throw new InvalidInputException("Date From must be older than Date To");

        if (origin != null && !flights.stream().anyMatch(h -> h.getOrigin().equalsIgnoreCase(origin)))
            throw new InvalidInputException("The chosen origin does not exist");

        if (destination != null && !flights.stream().anyMatch(h -> h.getDestination().equalsIgnoreCase(destination)))
            throw new InvalidInputException("The chosen destination does not exist");
    }
}
