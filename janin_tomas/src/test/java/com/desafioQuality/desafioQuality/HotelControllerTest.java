package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.controllers.HotelController;
import com.desafioQuality.desafioQuality.dtos.ErrorDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.services.HotelService;
import com.desafioQuality.desafioQuality.services.GeneralTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {
    @MockBean
    private HotelService hotelService;

    private HotelController hotelController;

    @BeforeEach
    void setUp() {
        hotelController = new HotelController(hotelService);
    }

    @Test
    @DisplayName("Gets all the hotels with no filters applied")
    public void getAllHotels() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotels();

        when(hotelService.findHotelsByFilters(any(), any(), any())).thenReturn(hotels);

        ResponseEntity<List<HotelDTO>> responseHotels = hotelController.hotels(null, null, null);

        Assertions.assertEquals(hotels, responseHotels.getBody());
    }

    @Test
    @DisplayName("Gets all the hotels with filters applied")
    public void getHotelsWithFilters() throws Exception {
        List<HotelDTO> hotels = GeneralTestUtils.getHotelsWithFilters();

        when(hotelService.findHotelsByFilters(any(), any(), any())).thenReturn(hotels);

        ResponseEntity<List<HotelDTO>> responseHotels = hotelController.hotels("12/02/2021", "25/02/21", "Buenos Aires");

        Assertions.assertEquals(hotels, responseHotels.getBody());
    }

    @Test
    @DisplayName("Gets an exception from an incorrect DateFrom")
    public void getDateFromException() throws Exception {
        when(hotelService.findHotelsByFilters(any(), any(), any())).thenThrow(new InvalidInputException("There are no hotels with the given input"));

        ResponseEntity thrown = hotelController.hotels("25/40/2021", "25/02/21", "Buenos Aires");

        ErrorDTO error = (ErrorDTO) thrown.getBody();

        Assertions.assertTrue(error.getName().contains("Invalid Input"));
        Assertions.assertTrue(error.getDescription().contains("There are no hotels with the given input"));
    }
}
