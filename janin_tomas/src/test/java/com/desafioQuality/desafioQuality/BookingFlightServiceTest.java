package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.dtos.*;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.FlightRepository;
import com.desafioQuality.desafioQuality.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(BookingFlightService.class)
public class BookingFlightServiceTest {
    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private FlightService flightService;

    private BookingFlightService bookingService;

    private List<FlightDTO> flights;

    @BeforeEach
    void setUp() throws ParseException {
        bookingService = new BookingFlightServiceImpl(flightService, flightRepository);
        flights = GeneralTestUtils.getFlights();
    }

    @Test
    @DisplayName("Reserves a flight successfully with 1 due")
    public void reserveAFlight1Due() throws Exception {
        DataBaseUtils.resetReservation();

        List<FlightDTO> flightBooking = GeneralTestUtils.getFlightBooking();

        BookingFlightRequestDTO bookingRequest = GeneralTestUtils.getFlightBookingRequest(1);

        BookingFlightResponseDTO bookingResponse = GeneralTestUtils.getFlightBookingResponse1Due();

        whenStatements(flightBooking);

        BookingFlightResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(bookingResponse, bookingResponseService);
    }

    @Test
    @DisplayName("Reserves a flight successfully with 3 dues")
    public void reserveAFlight3Dues() throws Exception {
        DataBaseUtils.resetReservation();

        List<FlightDTO> flightBooking = GeneralTestUtils.getFlightBooking();

        BookingFlightRequestDTO bookingRequest = GeneralTestUtils.getFlightBookingRequest(3);

        BookingFlightResponseDTO bookingResponse = GeneralTestUtils.getFlightBookingResponse3Dues();

        whenStatements(flightBooking);

        BookingFlightResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(bookingResponse, bookingResponseService);
    }

    @Test
    @DisplayName("Reserves a flight successfully with 6 dues")
    public void reserveAFlight6Dues() throws Exception {
        DataBaseUtils.resetReservation();

        List<FlightDTO> flightBooking = GeneralTestUtils.getFlightBooking();

        BookingFlightRequestDTO bookingRequest = GeneralTestUtils.getFlightBookingRequest(6);

        BookingFlightResponseDTO bookingResponse = GeneralTestUtils.getFlightBookingResponse6Dues();

        whenStatements(flightBooking);

        BookingFlightResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(bookingResponse, bookingResponseService);
    }

    @Test
    @DisplayName("Reserves a flight unsuccessfully because of a payment method with 12 dues")
    public void reserveAFlight12DuesException() throws Exception {
        DataBaseUtils.resetReservation();

        List<FlightDTO> flightBooking = GeneralTestUtils.getFlightBooking();

        BookingFlightRequestDTO bookingRequest = GeneralTestUtils.getFlightBookingRequest(12);

        whenStatements(flightBooking);

        BookingFlightResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The amount of dues can go from 1 to 6", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Reserves a flight unsuccessfully because there is not a flight with the given input")
    public void nonExistentFlightException() throws Exception {
        List<FlightDTO> flightBooking = new ArrayList<>();

        BookingFlightRequestDTO bookingRequest = GeneralTestUtils.getFlightBookingRequest(6);

        whenStatements(flightBooking);

        BookingFlightResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("There are no flights with the given input", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Reserves a flight unsuccessfully because of seats not being a numeric value")
    public void getNotNumericSeatsException() throws Exception {
        List<FlightDTO> flightBooking = new ArrayList<>();

        BookingFlightRequestDTO bookingRequest = GeneralTestUtils.getFlightBookingNotNumericSeats();

        whenStatements(flightBooking);

        BookingFlightResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The amount of seats must be a numeric value", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Reserves a flight unsuccessfully because of seats being more than 4")
    public void getSeatsOverflowException() throws Exception {
        List<FlightDTO> flightBooking = new ArrayList<>();

        BookingFlightRequestDTO bookingRequest = GeneralTestUtils.getFlightBookingSeatsOverflow();

        whenStatements(flightBooking);

        BookingFlightResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The maximum amount of seats allowed is 4", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Reserves a flight unsuccessfully because of seat type being invalid")
    public void getInvalidSeatTypeException() throws Exception {
        List<FlightDTO> flightBooking = new ArrayList<>();

        BookingFlightRequestDTO bookingRequest = GeneralTestUtils.getFlightBookingInvalidSeatType();

        whenStatements(flightBooking);

        BookingFlightResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The selected seat type does not exist", bookingResponseService.getStatusCode().getMessage());
    }

    private void whenStatements(List<FlightDTO> flightBooking) throws IOException, ParseException, InvalidInputException {
        when(flightRepository.findFlights(any())).thenReturn(flights);
        when(flightService.findFlightByDateFrom(any(), any())).thenReturn(flightBooking);
        when(flightService.findFlightByDateTo(any(), any())).thenReturn(flightBooking);
        when(flightService.findFlightByOrigin(any(), any())).thenReturn(flightBooking);
        when(flightService.findFlightByDestination(any(), any())).thenReturn(flightBooking);
        when(flightService.findFlightByFlightNumber(any(), any())).thenReturn(flightBooking);
        when(flightService.findFlightBySeatType(any(), any())).thenReturn(flightBooking);
    }
}
