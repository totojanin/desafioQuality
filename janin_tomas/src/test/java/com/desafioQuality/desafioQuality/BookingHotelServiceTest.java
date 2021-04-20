package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.dtos.BookingHotelRequestDTO;
import com.desafioQuality.desafioQuality.dtos.BookingHotelResponseDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.HotelRepository;
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

@WebMvcTest(BookingHotelService.class)
public class BookingHotelServiceTest {
    @MockBean
    private HotelRepository hotelRepository;

    @MockBean
    private HotelService hotelService;

    private BookingHotelService bookingService;

    private List<HotelDTO> hotels;

    @BeforeEach
    void setUp() throws ParseException {
        bookingService = new BookingHotelServiceImpl(hotelService, hotelRepository);
        hotels = GeneralTestUtils.getHotels();
    }

    @Test
    @DisplayName("Reserves a hotel successfully with 1 due")
    public void reserveAHotel1Due() throws Exception {
        DataBaseUtils.resetReservation();

        List<HotelDTO> hotelBooking = GeneralTestUtils.getHotelBooking();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingRequest(1);

        BookingHotelResponseDTO bookingResponse = GeneralTestUtils.getHotelBookingResponse1Due();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(bookingResponse, bookingResponseService);
    }

    @Test
    @DisplayName("Reserves a hotel successfully with 3 dues")
    public void reserveAHotel3Dues() throws Exception {
        DataBaseUtils.resetReservation();

        List<HotelDTO> hotelBooking = GeneralTestUtils.getHotelBooking();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingRequest(3);

        BookingHotelResponseDTO bookingResponse = GeneralTestUtils.getHotelBookingResponse3Dues();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(bookingResponse, bookingResponseService);
    }

    @Test
    @DisplayName("Reserves a hotel successfully with 6 dues")
    public void reserveAHotel6Dues() throws Exception {
        DataBaseUtils.resetReservation();

        List<HotelDTO> hotelBooking = GeneralTestUtils.getHotelBooking();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingRequest(6);

        BookingHotelResponseDTO bookingResponse = GeneralTestUtils.getHotelBookingResponse6Dues();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(bookingResponse, bookingResponseService);
    }

    @Test
    @DisplayName("Reserves a hotel unsuccessfully because of a payment method with 12 dues")
    public void reserveAHotel12DuesException() throws Exception {
        DataBaseUtils.resetReservation();

        List<HotelDTO> hotelBooking = GeneralTestUtils.getHotelBooking();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingRequest(12);

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The amount of dues can go from 1 to 6", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Reserves a hotel unsuccessfully because it is already reserved")
    public void hotelAlreadyReservedException() throws Exception {
        List<HotelDTO> hotelBooking = GeneralTestUtils.getReservedHotelBooking();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingRequest(6);

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The hotel is already reserved", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Reserves a hotel unsuccessfully because there is not a hotel with the given input")
    public void nonExistentHotelException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingRequest(6);

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("There are no hotels with the given input", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from people amount not being a numeric value")
    public void getNotNumericPeopleAmountException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingNotNumericPeopleAmount();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The amount of people must be a numeric value", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from people amount being different than room type")
    public void getInvalidSingleRoomTypeException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingInvalidSingleRoomType();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The selected room type does not match the amount of people staying in it", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from people amount being different than room type")
    public void getInvalidDoubleRoomTypeException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingInvalidDoubleRoomType();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The selected room type does not match the amount of people staying in it", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from people amount being different than room type")
    public void getInvalidTripleRoomTypeException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingInvalidTripleRoomType();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The selected room type does not match the amount of people staying in it", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from people amount being different than room type")
    public void getInvalidMultipleRoomTypeException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingInvalidMultipleRoomType();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The selected room type does not match the amount of people staying in it", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from people amount being more than 10")
    public void getPeopleAmountOverflowException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingPeopleAmountOverflow();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("The maximum amount of people allowed is 10", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from username being invalid")
    public void getInvalidUsernameException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingInvalidUsername();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("Please enter a valid email", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from people email being invalid")
    public void getInvalidPeopleEmailException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingInvalidPeopleEmail();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("Please enter a valid email", bookingResponseService.getStatusCode().getMessage());
    }

    @Test
    @DisplayName("Gets an exception from debit payment's dues being different from 1")
    public void getInvalidDebitPaymentException() throws Exception {
        List<HotelDTO> hotelBooking = new ArrayList<>();

        BookingHotelRequestDTO bookingRequest = GeneralTestUtils.getHotelBookingInvalidDebitPayment();

        whenStatements(hotelBooking);

        BookingHotelResponseDTO bookingResponseService = bookingService.reserve(bookingRequest);

        Assertions.assertEquals(400, bookingResponseService.getStatusCode().getCode());
        Assertions.assertEquals("If you pay with debit card, the amount of dues must be 1", bookingResponseService.getStatusCode().getMessage());
    }

    private void whenStatements(List<HotelDTO> hotelBooking) throws IOException, ParseException, InvalidInputException {
        when(hotelRepository.findHotels(any())).thenReturn(hotels);
        when(hotelService.findHotelByDateFrom(any(), any())).thenReturn(hotelBooking);
        when(hotelService.findHotelByDateTo(any(), any())).thenReturn(hotelBooking);
        when(hotelService.findHotelByDestination(any(), any())).thenReturn(hotelBooking);
        when(hotelService.findHotelByHotelCode(any(), any())).thenReturn(hotelBooking);
        when(hotelService.findHotelByRoomType(any(), any())).thenReturn(hotelBooking);
    }
}
