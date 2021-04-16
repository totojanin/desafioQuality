package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.controllers.FlightController;
import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.opencsv.CSVReader;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralTestUtils {
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static List<HotelDTO> getHotels() throws ParseException {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();

        hotels.add(new HotelDTO("CH-0002", "Cataratas Hotel", "Puerto Iguazú" , "Doble" , 6300, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "20/03/2021"), "NO"));
        hotels.add(new HotelDTO("CH-0003", "Cataratas Hotel 2", "Puerto Iguazú" , "Triple" , 8200, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "23/03/2021"), "NO"));
        hotels.add(new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires" , "Single" , 5435, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "19/03/2021"), "NO"));
        hotels.add(new HotelDTO("CH-0002", "Hotel Bristol 2", "Buenos Aires" , "Doble" , 7200, DateUtils.parse(DATE_FORMAT, "12/02/2021"), DateUtils.parse(DATE_FORMAT, "17/04/2021"), "NO"));

        return hotels;
    }

    public static List<HotelDTO> getHotelsReserved() throws ParseException {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();

        hotels.add(new HotelDTO("CH-0002", "Cataratas Hotel", "Puerto Iguazú" , "Doble" , 6300, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "20/03/2021"), "SI"));
        hotels.add(new HotelDTO("CH-0003", "Cataratas Hotel 2", "Puerto Iguazú" , "Triple" , 8200, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "23/03/2021"), "SI"));
        hotels.add(new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires" , "Single" , 5435, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "19/03/2021"), "SI"));
        hotels.add(new HotelDTO("CH-0002", "Hotel Bristol 2", "Buenos Aires" , "Doble" , 7200, DateUtils.parse(DATE_FORMAT, "12/02/2021"), DateUtils.parse(DATE_FORMAT, "17/04/2021"), "SI"));

        return hotels;
    }

    public static List<HotelDTO> getHotelsWithFilters() throws ParseException {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();

        hotels.add(new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires" , "Single" , 5435, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "19/03/2021"), "NO"));
        hotels.add(new HotelDTO("CH-0002", "Hotel Bristol 2", "Buenos Aires" , "Doble" , 7200, DateUtils.parse(DATE_FORMAT, "12/02/2021"), DateUtils.parse(DATE_FORMAT, "17/04/2021"), "NO"));

        return hotels;
    }

    public static List<FlightDTO> getFlights() throws ParseException {
        List<FlightDTO> flights = new ArrayList<FlightDTO>();

        flights.add(new FlightDTO("BAPI-1235", "Buenos Aires", "Puerto Iguazú" , "Economy" , 6500, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "15/02/2021")));
        flights.add(new FlightDTO("PIBA-1420", "Puerto Iguazú", "Bogotá" , "Business" , 43200, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "20/02/2021")));
        flights.add(new FlightDTO("PIBA-1420", "Puerto Iguazú", "Bogotá" , "Economy" , 25735, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "21/02/2021")));
        flights.add(new FlightDTO("BATU-5536", "Buenos Aires", "Tucumán" , "Economy" , 7320, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "17/02/2021")));

        return flights;
    }

    public static List<FlightDTO> getFlightsWithFilters() throws ParseException {
        List<FlightDTO> flights = new ArrayList<FlightDTO>();

        flights.add(new FlightDTO("PIBA-1420", "Puerto Iguazú", "Bogotá" , "Business" , 43200, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "20/02/2021")));
        flights.add(new FlightDTO("PIBA-1420", "Puerto Iguazú", "Bogotá" , "Economy" , 25735, DateUtils.parse(DATE_FORMAT, "10/02/2021"), DateUtils.parse(DATE_FORMAT, "21/02/2021")));

        return flights;
    }
}
