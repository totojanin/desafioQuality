package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.*;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void validateInputHotels(String dateFrom, String dateTo, String destination, List<HotelDTO> hotels) throws InvalidInputException, ParseException {
        validateDates(dateFrom, dateTo);

        validateHotelDestination(destination, hotels);
    }

    public static void validateInputFlights(String dateFrom, String dateTo, String origin, String destination, List<FlightDTO> flights) throws InvalidInputException, ParseException {
        validateDates(dateFrom, dateTo);

        validateFlightOrigin(origin, flights);

        validateFlightDestination(destination, flights);
    }

    public static void validateInputBookingHotel(BookingHotelRequestDTO bookingRequest, List<HotelDTO> hotels) throws InvalidInputException, ParseException {
        validateDates(bookingRequest.getBooking().getDateFrom(), bookingRequest.getBooking().getDateTo());

        validateHotelDestination(bookingRequest.getBooking().getDestination(), hotels);

        validatePeopleAmount(bookingRequest.getBooking().getPeopleAmount());

        validateRoomType(bookingRequest.getBooking().getRoomType(), bookingRequest.getBooking().getPeopleAmount());

        validateEmail(bookingRequest.getUserName(), bookingRequest.getBooking().getPeople());

        validatePaymentMethod(bookingRequest.getBooking().getPaymentMethod());
    }

    public static void validateInputBookingFlight(BookingFlightRequestDTO bookingRequest, List<FlightDTO> flights) throws InvalidInputException, ParseException {
        validateDates(bookingRequest.getFlightReservation().getDateFrom(), bookingRequest.getFlightReservation().getDateTo());

        validateFlightOrigin(bookingRequest.getFlightReservation().getOrigin(), flights);

        validateFlightDestination(bookingRequest.getFlightReservation().getDestination(), flights);

        validateSeats(bookingRequest.getFlightReservation().getSeats());

        validateSeatType(bookingRequest.getFlightReservation().getSeatType());

        validateEmail(bookingRequest.getUserName(), bookingRequest.getFlightReservation().getPeople());

        validatePaymentMethod(bookingRequest.getFlightReservation().getPaymentMethod());
    }

    private static void validateDates(String dateFrom, String dateTo) throws InvalidInputException, ParseException {
        if (dateFrom != null && !DateUtils.isValid(DATE_FORMAT, dateFrom))
            throw new InvalidInputException("The Date From format must be dd/mm/aaaa");

        if (dateTo != null && !DateUtils.isValid(DATE_FORMAT, dateTo))
            throw new InvalidInputException("The Date To format must be dd/mm/aaaa");

        if (dateFrom != null && dateTo != null && DateUtils.parse(DATE_FORMAT, dateFrom).toEpochDay() > DateUtils.parse(DATE_FORMAT, dateTo).toEpochDay())
            throw new InvalidInputException("Date From must be older than Date To");
    }

    private static void validateHotelDestination(String destination, List<HotelDTO> hotels) throws InvalidInputException {
        if (destination != null && !hotels.stream().anyMatch(h -> h.getDestination().equalsIgnoreCase(destination)))
            throw new InvalidInputException("The chosen destination does not exist");
    }

    private static void validateFlightOrigin(String origin, List<FlightDTO> flights) throws InvalidInputException {
        if (origin != null && !flights.stream().anyMatch(h -> h.getOrigin().equalsIgnoreCase(origin)))
            throw new InvalidInputException("The chosen origin does not exist");
    }

    private static void validateFlightDestination(String destination, List<FlightDTO> flights) throws InvalidInputException {
        if (destination != null && !flights.stream().anyMatch(h -> h.getDestination().equalsIgnoreCase(destination)))
            throw new InvalidInputException("The chosen destination does not exist");
    }

    private static void validatePeopleAmount(String peopleAmount) throws InvalidInputException {
        if (!isNumeric(peopleAmount))
            throw new InvalidInputException("The amount of people must be a numeric value");

        if (Integer.parseInt(peopleAmount) > 10)
            throw new InvalidInputException("The maximum amount of people allowed is 10");
    }

    private static void validateSeats(String seats) throws InvalidInputException {
        if (!isNumeric(seats))
            throw new InvalidInputException("The amount of seats must be a numeric value");

        if (Integer.parseInt(seats) > 4)
            throw new InvalidInputException("The maximum amount of seats allowed is 4");
    }

    private static void validateRoomType(String roomType, String peopleAmount) throws InvalidInputException {
        switch (roomType) {
            case "Single":
                if (Integer.parseInt(peopleAmount) != 1)
                    throw new InvalidInputException("The selected room type does not match the amount of people staying in it");
                break;

            case "Doble":
                if (Integer.parseInt(peopleAmount) != 2)
                    throw new InvalidInputException("The selected room type does not match the amount of people staying in it");
                break;

            case "Triple":
                if (Integer.parseInt(peopleAmount) != 3)
                    throw new InvalidInputException("The selected room type does not match the amount of people staying in it");
                break;

            case "Múltiple":
                if (Integer.parseInt(peopleAmount) < 4)
                    throw new InvalidInputException("The selected room type does not match the amount of people staying in it");
                break;

            default:
                throw new InvalidInputException("The selected room type does not exist");
        }
    }

    private static void validateSeatType(String seatType) throws InvalidInputException {
        if (!seatType.equalsIgnoreCase("Economy") && !seatType.equalsIgnoreCase("Business"))
            throw new InvalidInputException("The selected seat type does not exist");
    }

    private static void validateEmail(String email, List<PersonDTO> people) throws InvalidInputException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        if (!matcher.find())
            throw new InvalidInputException("Please enter a valid email");

        for (PersonDTO person : people) {
            matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(person.getMail());

            if (!matcher.find())
                throw new InvalidInputException("Please enter a valid email");
        }
    }

    private static void validatePaymentMethod(PaymentMethodDTO paymentMethod) throws InvalidInputException {
        if (paymentMethod.getType().equalsIgnoreCase("DEBIT")) {
            if (paymentMethod.getDues() != 1)
                throw new InvalidInputException("If you pay with debit card, the amount of dues must be 1");
        }
    }

    private static boolean isNumeric(String numberStr) {
        try {
            Integer.parseInt(numberStr);

            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
