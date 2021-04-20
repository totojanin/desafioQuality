package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.services.DataBaseUtils;
import com.desafioQuality.desafioQuality.services.DateUtils;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class FlightRepositoryImpl implements FlightRepository {
    @Override
    public List<FlightDTO> findFlights(String dateFormat) throws IOException, ParseException {
        return DataBaseUtils.loadFlightDataBase(dateFormat);
    }
}
