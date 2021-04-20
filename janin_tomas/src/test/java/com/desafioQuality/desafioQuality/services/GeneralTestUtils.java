package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GeneralTestUtils {
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static List<HotelDTO> getHotels() throws ParseException {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();

        hotels.add(new HotelDTO("CH-0002", "Cataratas Hotel", "Puerto Iguazú" , "Doble" , 6300, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "20/03/2021"), "NO"));
        hotels.add(new HotelDTO("CH-0003", "Cataratas Hotel 2", "Puerto Iguazú" , "Triple" , 8200, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "23/03/2021"), "NO"));
        hotels.add(new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires" , "Single" , 5435, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "19/03/2021"), "NO"));
        hotels.add(new HotelDTO("CH-0002", "Hotel Bristol 2", "Buenos Aires" , "Doble" , 7200, DateUtils.parse(DATE_FORMAT, "12/02/2021"), DateUtils.parse(DATE_FORMAT, "17/04/2021"), "NO"));
        hotels.add(new HotelDTO("SE-0001", "Selina", "Bogotá", "Single", 3900, DateUtils.parse(DATE_FORMAT, "01/11/2021"), DateUtils.parse(DATE_FORMAT, "23/11/2021"), "NO"));

        return hotels;
    }

    public static List<HotelDTO> getHotelsReserved() throws ParseException {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();

        hotels.add(new HotelDTO("CH-0002", "Cataratas Hotel", "Puerto Iguazú" , "Doble" , 6300, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "20/03/2021"), "SI"));
        hotels.add(new HotelDTO("CH-0003", "Cataratas Hotel 2", "Puerto Iguazú" , "Triple" , 8200, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "23/03/2021"), "SI"));
        hotels.add(new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires" , "Single" , 5435, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "19/03/2021"), "SI"));
        hotels.add(new HotelDTO("CH-0002", "Hotel Bristol 2", "Buenos Aires" , "Doble" , 7200, DateUtils.parse(DATE_FORMAT, "12/02/2021"), DateUtils.parse(DATE_FORMAT, "17/04/2021"), "SI"));

        return hotels;
    }

    public static List<HotelDTO> getHotelsWithFilters() throws ParseException {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();

        hotels.add(new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires" , "Single" , 5435, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "19/03/2021"), "NO"));
        hotels.add(new HotelDTO("CH-0002", "Hotel Bristol 2", "Buenos Aires" , "Doble" , 7200, DateUtils.parse(DATE_FORMAT, "12/02/2021"), DateUtils.parse(DATE_FORMAT, "17/04/2021"), "NO"));

        return hotels;
    }

    public static List<HotelDTO> getHotelBooking() throws ParseException {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();

        hotels.add(new HotelDTO("SE-0001", "Selina", "Bogotá", "Single", 3900, DateUtils.parse(DATE_FORMAT, "23/01/2021"), DateUtils.parse(DATE_FORMAT, "23/11/2021"), "NO"));

        return hotels;
    }

    public static List<HotelDTO> getReservedHotelBooking() throws ParseException {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();

        hotels.add(new HotelDTO("SE-0001", "Selina", "Bogotá", "Single", 3900, DateUtils.parse(DATE_FORMAT, "23/01/2021"), DateUtils.parse(DATE_FORMAT, "23/11/2021"), "SI"));

        return hotels;
    }

    public static List<FlightDTO> getFlights() throws ParseException {
        List<FlightDTO> flights = new ArrayList<FlightDTO>();

        flights.add(new FlightDTO("BAPI-1235", "Buenos Aires", "Puerto Iguazú" , "Economy" , 6500, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "15/02/2021")));
        flights.add(new FlightDTO("PIBA-1420", "Puerto Iguazú", "Bogotá" , "Business" , 43200, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "20/02/2021")));
        flights.add(new FlightDTO("PIBA-1420", "Puerto Iguazú", "Bogotá" , "Economy" , 25735, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "21/02/2021")));
        flights.add(new FlightDTO("BATU-5536", "Buenos Aires", "Tucumán" , "Economy" , 7320, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "17/02/2021")));

        return flights;
    }

    public static List<FlightDTO> getFlightsWithFilters() throws ParseException {
        List<FlightDTO> flights = new ArrayList<FlightDTO>();

        flights.add(new FlightDTO("PIBA-1420", "Puerto Iguazú", "Bogotá" , "Business" , 43200, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "20/02/2021")));
        flights.add(new FlightDTO("PIBA-1420", "Puerto Iguazú", "Bogotá" , "Economy" , 25735, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "21/02/2021")));

        return flights;
    }

    public static List<FlightDTO> getFlightBooking() throws ParseException {
        List<FlightDTO> flights = new ArrayList<FlightDTO>();

        flights.add(new FlightDTO("BAPI-1235", "Buenos Aires", "Puerto Iguazú" , "Economy" , 6500, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "15/02/2021")));

        return flights;
    }

    public static BookingHotelRequestDTO getHotelBookingRequest(int dues) {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", dues);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "1", "Single", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelNoPaymentDTO getHotelBookingNoPayment() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        BookingHotelNoPaymentDTO booking = new BookingHotelNoPaymentDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "1", "Single", people);

        return booking;
    }

    public static BookingHotelRequestDTO getHotelBookingNotNumericPeopleAmount() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "a", "Single", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelRequestDTO getHotelBookingPeopleAmountOverflow() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "11", "Single", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelRequestDTO getHotelBookingInvalidSingleRoomType() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "2", "Single", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelRequestDTO getHotelBookingInvalidDoubleRoomType() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "3", "Doble", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelRequestDTO getHotelBookingInvalidTripleRoomType() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "5", "Triple", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelRequestDTO getHotelBookingInvalidMultipleRoomType() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "3", "Múltiple", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelRequestDTO getHotelBookingInvalidUsername() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "1", "Single", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez", booking);

        return bookingRequest;
    }

    public static BookingHotelRequestDTO getHotelBookingInvalidPeopleEmail() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "1", "Single", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelRequestDTO getHotelBookingInvalidDebitPayment() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("DEBIT", "1234-1234-1234-1234", 6);

        BookingHotelDTO booking = new BookingHotelDTO("10/11/2021", "20/11/2021", "Bogotá", "SE-0001", "1", "Single", people, paymentMethod);

        BookingHotelRequestDTO bookingRequest = new BookingHotelRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingHotelResponseDTO getHotelBookingResponse1Due() {
        BookingHotelResponseDTO bookingResponse = new BookingHotelResponseDTO();

        BookingHotelNoPaymentDTO bookingRequest = GeneralTestUtils.getHotelBookingNoPayment();

        StatusCodeDTO statusCode = new StatusCodeDTO(200, "The process ended satisfactorily");

        bookingResponse.setUserName("seba_gonzalez@unmail.com");
        bookingResponse.setAmount(3900);
        bookingResponse.setInterest(0);
        bookingResponse.setTotal(3900);
        bookingResponse.setBooking(bookingRequest);
        bookingResponse.setStatusCode(statusCode);

        return bookingResponse;
    }

    public static BookingHotelResponseDTO getHotelBookingResponse3Dues() {
        BookingHotelResponseDTO bookingResponse = new BookingHotelResponseDTO();

        BookingHotelNoPaymentDTO bookingRequest = GeneralTestUtils.getHotelBookingNoPayment();

        StatusCodeDTO statusCode = new StatusCodeDTO(200, "The process ended satisfactorily");

        bookingResponse.setUserName("seba_gonzalez@unmail.com");
        bookingResponse.setAmount(3900);
        bookingResponse.setInterest(5);
        bookingResponse.setTotal(4095);
        bookingResponse.setBooking(bookingRequest);
        bookingResponse.setStatusCode(statusCode);

        return bookingResponse;
    }

    public static BookingHotelResponseDTO getHotelBookingResponse6Dues() {
        BookingHotelResponseDTO bookingResponse = new BookingHotelResponseDTO();

        BookingHotelNoPaymentDTO bookingRequest = GeneralTestUtils.getHotelBookingNoPayment();

        StatusCodeDTO statusCode = new StatusCodeDTO(200, "The process ended satisfactorily");

        bookingResponse.setUserName("seba_gonzalez@unmail.com");
        bookingResponse.setAmount(3900);
        bookingResponse.setInterest(10);
        bookingResponse.setTotal(4290);
        bookingResponse.setBooking(bookingRequest);
        bookingResponse.setStatusCode(statusCode);

        return bookingResponse;
    }

    public static BookingFlightRequestDTO getFlightBookingRequest(int dues) {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", dues);

        BookingFlightDTO booking = new BookingFlightDTO("10/02/2021", "20/02/2021", "Puerto Iguazú", "Bogotá", "PIBA-1420", "1", "Business", people, paymentMethod);

        BookingFlightRequestDTO bookingRequest = new BookingFlightRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingFlightNoPaymentDTO getFlightBookingNoPayment() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        BookingFlightNoPaymentDTO booking = new BookingFlightNoPaymentDTO("10/02/2021", "20/02/2021", "Puerto Iguazú", "Bogotá", "PIBA-1420", "1", "Business", people);

        return booking;
    }

    public static BookingFlightRequestDTO getFlightBookingNotNumericSeats() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingFlightDTO booking = new BookingFlightDTO("10/02/2021", "20/02/2021", "Puerto Iguazú", "Bogotá", "PIBA-1420", "a", "Business", people, paymentMethod);

        BookingFlightRequestDTO bookingRequest = new BookingFlightRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingFlightRequestDTO getFlightBookingSeatsOverflow() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingFlightDTO booking = new BookingFlightDTO("10/02/2021", "20/02/2021", "Puerto Iguazú", "Bogotá", "PIBA-1420", "5", "Business", people, paymentMethod);

        BookingFlightRequestDTO bookingRequest = new BookingFlightRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingFlightRequestDTO getFlightBookingInvalidSeatType() {
        PersonDTO person1 = new PersonDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com");
        PersonDTO person2 = new PersonDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com");

        List<PersonDTO> people = new ArrayList<>();

        people.add(person1);
        people.add(person2);

        PaymentMethodDTO paymentMethod = new PaymentMethodDTO("CREDIT", "1234-1234-1234-1234", 6);

        BookingFlightDTO booking = new BookingFlightDTO("10/02/2021", "20/02/2021", "Puerto Iguazú", "Bogotá", "PIBA-1420", "1", "First Class", people, paymentMethod);

        BookingFlightRequestDTO bookingRequest = new BookingFlightRequestDTO("seba_gonzalez@unmail.com", booking);

        return bookingRequest;
    }

    public static BookingFlightResponseDTO getFlightBookingResponse1Due() {
        BookingFlightResponseDTO bookingResponse = new BookingFlightResponseDTO();

        BookingFlightNoPaymentDTO bookingRequest = GeneralTestUtils.getFlightBookingNoPayment();

        StatusCodeDTO statusCode = new StatusCodeDTO(200, "The process ended satisfactorily");

        bookingResponse.setUserName("seba_gonzalez@unmail.com");
        bookingResponse.setAmount(6500);
        bookingResponse.setInterest(0);
        bookingResponse.setTotal(6500);
        bookingResponse.setBooking(bookingRequest);
        bookingResponse.setStatusCode(statusCode);

        return bookingResponse;
    }

    public static BookingFlightResponseDTO getFlightBookingResponse3Dues() {
        BookingFlightResponseDTO bookingResponse = new BookingFlightResponseDTO();

        BookingFlightNoPaymentDTO bookingRequest = GeneralTestUtils.getFlightBookingNoPayment();

        StatusCodeDTO statusCode = new StatusCodeDTO(200, "The process ended satisfactorily");

        bookingResponse.setUserName("seba_gonzalez@unmail.com");
        bookingResponse.setAmount(6500);
        bookingResponse.setInterest(5);
        bookingResponse.setTotal(6825);
        bookingResponse.setBooking(bookingRequest);
        bookingResponse.setStatusCode(statusCode);

        return bookingResponse;
    }

    public static BookingFlightResponseDTO getFlightBookingResponse6Dues() {
        BookingFlightResponseDTO bookingResponse = new BookingFlightResponseDTO();

        BookingFlightNoPaymentDTO bookingRequest = GeneralTestUtils.getFlightBookingNoPayment();

        StatusCodeDTO statusCode = new StatusCodeDTO(200, "The process ended satisfactorily");

        bookingResponse.setUserName("seba_gonzalez@unmail.com");
        bookingResponse.setAmount(6500);
        bookingResponse.setInterest(10);
        bookingResponse.setTotal(7150);
        bookingResponse.setBooking(bookingRequest);
        bookingResponse.setStatusCode(statusCode);

        return bookingResponse;
    }
}
