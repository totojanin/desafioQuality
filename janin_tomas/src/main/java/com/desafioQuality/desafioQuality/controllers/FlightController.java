package com.desafioQuality.desafioQuality.controllers;

import com.desafioQuality.desafioQuality.dtos.*;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.services.BookingFlightService;
import com.desafioQuality.desafioQuality.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FlightController {
    private FlightService flightService;

    private BookingFlightService bookingService;

    public FlightController(FlightService flightService, BookingFlightService bookingService) {
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    @GetMapping("/flights")
    public ResponseEntity<List<FlightDTO>> flights(@RequestParam(value = "dateFrom", required = false) String dateFrom,
                                                 @RequestParam(value = "dateTo", required = false) String dateTo,
                                                 @RequestParam(value = "origin", required = false) String origin,
                                                 @RequestParam(value = "destination", required = false) String destination) throws InvalidInputException, IOException, ParseException {

        try {
            List<FlightDTO> flights = flightService.findFlightsByFilters(dateFrom, dateTo, origin, destination);

            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
        catch (InvalidInputException e) {
            return exceptionHandler(e);
        }
    }

    @PostMapping("/flight-reservation")
    public ResponseEntity<BookingFlightResponseDTO> flightReservation(@RequestBody BookingFlightRequestDTO bookingRequest) throws InvalidInputException, IOException, ParseException {
        try {
            BookingFlightResponseDTO bookingResponse = bookingService.reserve(bookingRequest);

            return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
        }
        catch (InvalidInputException e) {
            return exceptionHandler(e);
        }
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity exceptionHandler(InvalidInputException e) {
        return new ResponseEntity(new ErrorDTO(e.getTitle(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
