package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.*;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class BookingFlightServiceImpl implements BookingFlightService {
    private final String DATE_FORMAT = "dd/MM/yyyy";

    private FlightService flightService;

    private FlightRepository flightRepository;

    public BookingFlightServiceImpl(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

    @Override
    public BookingFlightResponseDTO reserve(BookingFlightRequestDTO bookingRequest) throws IOException, ParseException {
        try {
            List<FlightDTO> flights = flightRepository.findFlights(DATE_FORMAT);

            ValidationUtils.validateInputBookingFlight(bookingRequest, flights);

            BookingFlightResponseDTO bookingResponse = new BookingFlightResponseDTO();

            bookingResponse.setUserName(bookingRequest.getUserName());
            bookingResponse.setBooking(bookingRequest.getFlightReservation());

            FlightDTO flight = findFlightByBookingFilters(bookingRequest, flights);

            double pricePerPerson = flight.getPricePerPerson();

            bookingResponse.setAmount(pricePerPerson);

            double interest = getInterest(bookingRequest);

            bookingResponse.setInterest(interest);

            double total = round(pricePerPerson * (1 + interest / 100), 1);

            bookingResponse.setTotal(total);

            StatusCodeDTO statusCode = new StatusCodeDTO(200, "The process ended satisfactorily");

            bookingResponse.setStatusCode(statusCode);

            return bookingResponse;
        }
        catch (InvalidInputException e) {
            BookingFlightResponseDTO bookingResponse = new BookingFlightResponseDTO();

            StatusCodeDTO statusCode = new StatusCodeDTO(400, e.getMessage());

            bookingResponse.setStatusCode(statusCode);

            return bookingResponse;
        }
    }

    private FlightDTO findFlightByBookingFilters(BookingFlightRequestDTO bookingRequest, List<FlightDTO> flights) throws InvalidInputException, ParseException {
        flights = flightService.findFlightByDateFrom(flights, bookingRequest.getFlightReservation().getDateFrom());
        flights = flightService.findFlightByDateTo(flights, bookingRequest.getFlightReservation().getDateTo());
        flights = flightService.findFlightByOrigin(flights, bookingRequest.getFlightReservation().getOrigin());
        flights = flightService.findFlightByDestination(flights, bookingRequest.getFlightReservation().getDestination());
        flights = flightService.findFlightByFlightNumber(flights, bookingRequest.getFlightReservation().getFlightNumber());
        flights = flightService.findFlightBySeatType(flights, bookingRequest.getFlightReservation().getSeatType());

        if (flights.size() > 0) {
            return flights.get(0);
        }
        else
            throw new InvalidInputException("There are no flights with the given input");
    }

    private double getInterest(BookingFlightRequestDTO bookingRequest) throws InvalidInputException {
        switch (bookingRequest.getFlightReservation().getPaymentMethod().getDues()) {
            case 1:
                return 0;

            case 2:
            case 3:
                return 5;

            case 4:
            case 5:
            case 6:
                return 10;

            default:
                throw new InvalidInputException("The amount of dues can go from 1 to 6");
        }
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
