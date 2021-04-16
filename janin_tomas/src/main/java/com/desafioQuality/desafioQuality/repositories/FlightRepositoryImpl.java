package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
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
    public List<FlightDTO> findFlights(String dateFormat) throws IOException {
        List<FlightDTO> db = loadDataBase(dateFormat);

        List<FlightDTO> flights = new ArrayList<>();

        if (db != null) {
            flights = db;
        }

        return flights;
    }

    private List<FlightDTO> loadDataBase(String dateFormat) throws IOException {
        List<FlightDTO> flights = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:dbFlights.csv").getPath()), ',');

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                flights.add(getRecordFromLine(nextLine, dateFormat));
            }

            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return flights;
    }

    private FlightDTO getRecordFromLine(String[] nextLine, String dateFormat) {
        FlightDTO flight = new FlightDTO();

        try {
            List<String> lineAsList = new ArrayList<String>(Arrays.asList(nextLine));

            flight = mapToFlightDTO(lineAsList, dateFormat);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return flight;
    }

    private FlightDTO mapToFlightDTO(List<String> values, String dateFormat) throws ParseException {
        String flightNumber = values.get(0);
        String origin = values.get(1);
        String destination = values.get(2);
        String seatType = values.get(3);
        double pricePerPerson = Double.parseDouble(values.get(4).replace("$", "").replace(".", ""));
        LocalDate dateFrom = DateUtils.parse(dateFormat, values.get(5));
        LocalDate dateTo = DateUtils.parse(dateFormat, values.get(6));

        return new FlightDTO(flightNumber, origin, destination, seatType, pricePerPerson, dateFrom, dateTo);
    }
}
