package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.controllers.FlightController;
import com.desafioQuality.desafioQuality.dtos.ErrorDTO;
import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.services.FlightService;
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

@WebMvcTest(FlightController.class)
public class FlightControllerTest {
    @MockBean
    private FlightService flightService;

    private FlightController flightController;

    @BeforeEach
    void setUp() {
        flightController = new FlightController(flightService);
    }

    @Test
    @DisplayName("Gets all the flights with no filters applied")
    public void getAllFlights() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightService.findFlightsByFilters(any(), any(), any(), any())).thenReturn(flights);

        ResponseEntity<List<FlightDTO>> responseFlights = flightController.flights(null, null, null, null);

        Assertions.assertEquals(flights, responseFlights.getBody());
    }

    @Test
    @DisplayName("Gets all the flights with filters applied")
    public void getFlightsWithFilters() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlightsWithFilters();

        when(flightService.findFlightsByFilters(any(), any(), any(), any())).thenReturn(flights);

        ResponseEntity<List<FlightDTO>> responseFlights = flightController.flights("12/02/2021", "18/02/21", "Puerto Iguazú", "Bogotá");

        Assertions.assertEquals(flights, responseFlights.getBody());
    }

    @Test
    @DisplayName("Gets an exception from an incorrect DateFrom")
    public void getDateFromException() throws Exception {
        when(flightService.findFlightsByFilters(any(), any(), any(), any())).thenThrow(new InvalidInputException("There are no flights with the given input"));

        ResponseEntity thrown = flightController.flights("25/40/2021", "18/02/21", "Puerto Iguazú", "Bogotá");

        ErrorDTO error = (ErrorDTO) thrown.getBody();

        Assertions.assertTrue(error.getName().contains("Invalid Input"));
        Assertions.assertTrue(error.getDescription().contains("There are no flights with the given input"));
    }
}
