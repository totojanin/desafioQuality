package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.FlightRepository;
import com.desafioQuality.desafioQuality.services.FlightService;
import com.desafioQuality.desafioQuality.services.FlightServiceImpl;
import com.desafioQuality.desafioQuality.services.GeneralTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(FlightService.class)
public class FlightServiceTest {
    @MockBean
    private FlightRepository flightRepository;

    private FlightService flightService;

    @BeforeEach
    void setUp() {
        flightService = new FlightServiceImpl(flightRepository);
    }

    @Test
    @DisplayName("Gets all the flights with no filters applied")
    public void getAllFlights() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        List<FlightDTO> responseFlights = flightService.findFlightsByFilters(null, null, null, null);

        Assertions.assertEquals(flights, responseFlights);
    }

    @Test
    @DisplayName("Gets all the flights with filters applied")
    public void getFlightsWithFilters() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();
        List<FlightDTO> flightsFiltered = GeneralTestUtils.getFlightsWithFilters();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        List<FlightDTO> responseFlights = flightService.findFlightsByFilters("12/02/2021", "18/02/2021", "Puerto Iguazú", "Bogotá");

        Assertions.assertEquals(flightsFiltered, responseFlights);
    }

    @Test
    @DisplayName("Gets an exception from an incorrect DateFrom")
    public void getDateFromException() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> flightService.findFlightsByFilters("40/25/2021", "18/02/21", "Puerto Iguazú", "Bogotá"),
                "Expected findFlightsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("The Date From format must be dd/mm/aaaa"));
    }

    @Test
    @DisplayName("Gets an exception from an incorrect DateTo")
    public void getDateToException() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> flightService.findFlightsByFilters("12/02/2021", "40/25/2021", "Puerto Iguazú", "Bogotá"),
                "Expected findFlightsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("The Date To format must be dd/mm/aaaa"));
    }

    @Test
    @DisplayName("Gets an exception because of DateFrom being later than DateTo")
    public void getDateFromLaterThanDateToException() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> flightService.findFlightsByFilters("18/02/2021", "12/02/2021", "Puerto Iguazú", "Bogotá"),
                "Expected findFlightsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("Date From must be older than Date To"));
    }

    @Test
    @DisplayName("Gets an exception from an non-existent Origin")
    public void getOriginException() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> flightService.findFlightsByFilters("12/02/2021", "25/02/2021", "Rosario", "Bogotá"),
                "Expected findFlightsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("The chosen origin does not exist"));
    }

    @Test
    @DisplayName("Gets an exception from an non-existent Destination")
    public void getDestinationException() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> flightService.findFlightsByFilters("12/02/2021", "25/02/2021", "Puerto Iguazú", "Rosario"),
                "Expected findFlightsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("The chosen destination does not exist"));
    }

    @Test
    @DisplayName("Gets an exception from flights not having the desired DateFrom")
    public void getNoFlightsDateFromException() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> flightService.findFlightsByFilters("15/05/2021", "25/05/2021", "Puerto Iguazú", "Bogotá"),
                "Expected findFlightsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("There are no flights with the given input"));
    }

    @Test
    @DisplayName("Gets an exception from flights not having the desired DateTo")
    public void getNoFlightsDateToException() throws Exception {
        List<FlightDTO> flights = GeneralTestUtils.getFlights();

        when(flightRepository.findFlights(any())).thenReturn(flights);

        InvalidInputException thrown = Assertions.assertThrows(
                InvalidInputException.class,
                () -> flightService.findFlightsByFilters("12/02/2021", "25/05/2021", "Puerto Iguazú", "Bogotá"),
                "Expected findFlightsByFilters() to throw, but it didn't"
        );

        Assertions.assertTrue(thrown.getMessage().contains("There are no flights with the given input"));
    }
}
