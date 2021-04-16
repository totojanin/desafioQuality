package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;

import java.io.IOException;
import java.util.List;

public interface FlightRepository {
    List<FlightDTO> findFlights(String dateFormat) throws IOException;
}
