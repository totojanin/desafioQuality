package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.repositories.FlightRepository;
import com.desafioQuality.desafioQuality.repositories.FlightRepositoryImpl;
import com.desafioQuality.desafioQuality.repositories.HotelRepositoryImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

@WebMvcTest(FlightRepository.class)
public class FlightRepositoryTest {
    private FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        flightRepository = new FlightRepositoryImpl();
    }

    @Test
    @DisplayName("Gets all the flights in the repository")
    public void getAllFlights() throws Exception {
        List<FlightDTO> responseFlights = flightRepository.findFlights("dd/MM/yyyy");

        Assertions.assertEquals(12, responseFlights.size());
    }
}
