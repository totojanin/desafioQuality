package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.BookingHotelRequestDTO;
import com.desafioQuality.desafioQuality.dtos.BookingHotelResponseDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.dtos.StatusCodeDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class BookingHotelServiceImpl implements BookingHotelService {
    private final String DATE_FORMAT = "dd/MM/yyyy";

    private HotelService hotelService;

    private HotelRepository hotelRepository;

    public BookingHotelServiceImpl(HotelService hotelService, HotelRepository hotelRepository) {
        this.hotelService = hotelService;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public BookingHotelResponseDTO reserve(BookingHotelRequestDTO bookingRequest) throws IOException, ParseException {
        try {
            List<HotelDTO> hotels = hotelRepository.findHotels(DATE_FORMAT);

            ValidationUtils.validateInputBookingHotel(bookingRequest, hotels);

            BookingHotelResponseDTO bookingResponse = new BookingHotelResponseDTO();

            bookingResponse.setUserName(bookingRequest.getUserName());
            bookingResponse.setBooking(bookingRequest.getBooking());

            HotelDTO hotel = findHotelByBookingFilters(bookingRequest, hotels);

            double pricePerNight = hotel.getPricePerNight();

            bookingResponse.setAmount(pricePerNight);

            double interest = getInterest(bookingRequest);

            bookingResponse.setInterest(interest);

            double total = round(pricePerNight * (1 + interest / 100), 1);

            bookingResponse.setTotal(total);

            StatusCodeDTO statusCode = new StatusCodeDTO(200, "The process ended satisfactorily");

            bookingResponse.setStatusCode(statusCode);

            DataBaseUtils.reserveHotel(hotel);

            return bookingResponse;
        }
        catch (InvalidInputException e) {
            BookingHotelResponseDTO bookingResponse = new BookingHotelResponseDTO();

            StatusCodeDTO statusCode = new StatusCodeDTO(400, e.getMessage());

            bookingResponse.setStatusCode(statusCode);

            return bookingResponse;
        }
    }

    private HotelDTO findHotelByBookingFilters(BookingHotelRequestDTO bookingRequest, List<HotelDTO> hotels) throws InvalidInputException, IOException, ParseException {
        hotels = hotelService.findHotelByDateFrom(hotels, bookingRequest.getBooking().getDateFrom());
        hotels = hotelService.findHotelByDateTo(hotels, bookingRequest.getBooking().getDateTo());
        hotels = hotelService.findHotelByDestination(hotels, bookingRequest.getBooking().getDestination());
        hotels = hotelService.findHotelByHotelCode(hotels, bookingRequest.getBooking().getHotelCode());
        hotels = hotelService.findHotelByRoomType(hotels, bookingRequest.getBooking().getRoomType());

        if (hotels.size() > 0) {
            HotelDTO hotel = hotels.get(0);

            if (hotel.getReserved().equalsIgnoreCase("NO"))
                return hotel;
            else
                throw new InvalidInputException("The hotel is already reserved");
        }
        else
            throw new InvalidInputException("There are no hotels with the given input");
    }

    private double getInterest(BookingHotelRequestDTO bookingRequest) throws InvalidInputException {
        switch (bookingRequest.getBooking().getPaymentMethod().getDues()) {
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

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
