package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.HotelRepository;
import com.desafioQuality.desafioQuality.services.HotelService;
import com.desafioQuality.desafioQuality.services.GeneralTestUtils;

import com.desafioQuality.desafioQuality.services.HotelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(HotelService.class)
public class HotelServiceTest {
    @MockBean
    private HotelRepository hotelRepository;

    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotelService = new HotelServiceImpl(hotelRepository);
    }

    @Test
    @DisplayName("Gets all the hotels with no filters applied")
    public void getAllHotels() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        List<HotelDTO> responseHotels = hotelService.findHotelsByFilters(null, null, null);

        Assertions.assertEquals(hotels, responseHotels);
    }

    @Test
    @DisplayName("Gets all the hotels with filters applied")
    public void getHotelsWithFilters() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();
        List<HotelDTO> hotelsFiltered = GeneralTestUtils.getHotelsWithFilters();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        List<HotelDTO> responseHotels = hotelService.findHotelsByFilters("12/02/2021", "25/02/2021", "Buenos Aires");

        Assertions.assertEquals(hotelsFiltered, responseHotels);
    }

    @Test
    @DisplayName("Gets an exception from an incorrect DateFrom")
    public void getDateFromException() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> hotelService.findHotelsByFilters("40/25/2021", "25/02/2021", "Buenos Aires"),
                "Expected findHotelsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("The Date From format must be dd/mm/aaaa"));
    }

    @Test
    @DisplayName("Gets an exception from an incorrect DateTo")
    public void getDateToException() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> hotelService.findHotelsByFilters("12/02/2021", "40/25/2021", "Buenos Aires"),
                "Expected findHotelsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("The Date To format must be dd/mm/aaaa"));
    }

    @Test
    @DisplayName("Gets an exception because of DateFrom being later than DateTo")
    public void getDateFromLaterThanDateToException() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> hotelService.findHotelsByFilters("25/02/2021", "12/02/2021", "Buenos Aires"),
                "Expected findHotelsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("Date From must be older than Date To"));
    }

    @Test
    @DisplayName("Gets an exception from an non-existent Destination")
    public void getDestinationException() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> hotelService.findHotelsByFilters("12/02/2021", "25/02/2021", "Rosario"),
                "Expected findHotelsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("The chosen destination does not exist"));
    }

    @Test
    @DisplayName("Gets an exception from hotels not having the desired DateFrom")
    public void getNoHotelsDateFromException() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> hotelService.findHotelsByFilters("15/05/2021", "25/05/2021", "Buenos Aires"),
                "Expected findHotelsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("There are no hotels with the given input"));
    }

    @Test
    @DisplayName("Gets an exception from hotels not having the desired DateTo")
    public void getNoHotelsDateToException() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> hotelService.findHotelsByFilters("12/02/2021", "25/05/2021", "Buenos Aires"),
                "Expected findHotelsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("There are no hotels with the given input"));
    }

    @Test
    @DisplayName("Gets an exception from hotels already being reserved")
    public void getNoHotelsReservedToException() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotelsReserved();

        when(hotelRepository.findHotels(any())).thenReturn(hotels);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> hotelService.findHotelsByFilters("12/02/2021", "20/02/2021", "Buenos Aires"),
                "Expected findHotelsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("There are no hotels available"));
    }
}
