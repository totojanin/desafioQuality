package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.BookingRequestDTO;
import com.desafioQuality.desafioQuality.dtos.BookingResponseDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final String DATE_FORMAT = "dd/MM/yyyy";

    private HotelRepository hotelRepository;

    public BookingServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public BookingResponseDTO reserve(BookingRequestDTO bookingRequest) throws InvalidInputException, IOException, ParseException {
        List<HotelDTO> hotels = hotelRepository.findHotels(DATE_FORMAT);

        validateInput(bookingRequest, hotels);

        BookingResponseDTO bookingResponse = new BookingResponseDTO();

        return bookingResponse;
    }

    private void validateInput(BookingRequestDTO bookingRequest, List<HotelDTO> hotels) throws InvalidInputException, ParseException {
        if (bookingRequest.getBooking().getDateFrom() != null && !DateUtils.isValid(DATE_FORMAT, bookingRequest.getBooking().getDateFrom()))
            throw new InvalidInputException("The Date From format must be dd/mm/aaaa");

        if (bookingRequest.getBooking().getDateTo() != null && !DateUtils.isValid(DATE_FORMAT, bookingRequest.getBooking().getDateTo()))
            throw new InvalidInputException("The Date To format must be dd/mm/aaaa");

        if (bookingRequest.getBooking().getDateFrom() != null && bookingRequest.getBooking().getDateTo() != null && DateUtils.parse(DATE_FORMAT, bookingRequest.getBooking().getDateFrom()).toEpochDay() > DateUtils.parse(DATE_FORMAT, bookingRequest.getBooking().getDateTo()).toEpochDay())
            throw new InvalidInputException("Date From must be older than Date To");

        if (bookingRequest.getBooking().getDestination() != null && !hotels.stream().anyMatch(h -> h.getDestination().equalsIgnoreCase(bookingRequest.getBooking().getDestination())))
            throw new InvalidInputException("The chosen destination does not exist");
    }
}
