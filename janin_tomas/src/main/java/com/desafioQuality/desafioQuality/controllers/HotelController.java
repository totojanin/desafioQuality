package com.desafioQuality.desafioQuality.controllers;

import com.desafioQuality.desafioQuality.dtos.BookingHotelRequestDTO;
import com.desafioQuality.desafioQuality.dtos.BookingHotelResponseDTO;
import com.desafioQuality.desafioQuality.dtos.ErrorDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.services.BookingHotelService;
import com.desafioQuality.desafioQuality.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HotelController {
    private HotelService hotelService;

    private BookingHotelService bookingService;

    public HotelController(HotelService hotelService, BookingHotelService bookingService) {
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDTO>> hotels(@RequestParam(value = "dateFrom", required = false) String dateFrom,
                                                   @RequestParam(value = "dateTo", required = false) String dateTo,
                                                   @RequestParam(value = "destination", required = false) String destination) throws InvalidInputException, IOException, ParseException {

        try {
            List<HotelDTO> hotels = hotelService.findHotelsByFilters(dateFrom, dateTo, destination);

            return new ResponseEntity<>(hotels, HttpStatus.OK);
        }
        catch (InvalidInputException e) {
            return exceptionHandler(e);
        }
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingHotelResponseDTO> booking(@RequestBody BookingHotelRequestDTO bookingRequest) throws InvalidInputException, IOException, ParseException {
        try {
            BookingHotelResponseDTO bookingResponse = bookingService.reserve(bookingRequest);

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
