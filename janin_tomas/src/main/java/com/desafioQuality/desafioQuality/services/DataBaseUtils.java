package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseUtils {
    public static List<HotelDTO> loadHotelDataBase(String dateFormat) {
        List<HotelDTO> db = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:dbHotels.csv").getPath()), ',');

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                db.add(getHotelRecordFromLine(nextLine, dateFormat));
            }

            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<HotelDTO> hotels = new ArrayList<>();

        if (db != null) {
            hotels = db;
        }

        return hotels;
    }

    private static HotelDTO getHotelRecordFromLine(String[] nextLine, String dateFormat) {
        HotelDTO hotel = new HotelDTO();

        try {
            List<String> lineAsList = new ArrayList<String>(Arrays.asList(nextLine));

            hotel = mapToHotelDTO(lineAsList, dateFormat);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return hotel;
    }

    private static HotelDTO mapToHotelDTO(List<String> values, String dateFormat) throws ParseException {
        String hotelCode = values.get(0);
        String name = values.get(1);
        String destination = values.get(2);
        String roomType = values.get(3);
        double pricePerNight = Double.parseDouble(values.get(4).replace("$", "").replace(".", ""));
        LocalDate availableFrom = DateUtils.parse(dateFormat, values.get(5));
        LocalDate availableTo = DateUtils.parse(dateFormat, values.get(6));
        String reserved = values.get(7);

        return new HotelDTO(hotelCode, name, destination, roomType, pricePerNight, availableFrom, availableTo, reserved);
    }

    public static boolean reserveHotel(HotelDTO hotel) {
        HotelDTO hotelDB = null;

        int row = 1;

        try {
            CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:dbHotels.csv").getPath()), ',');

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                hotelDB = getHotelRecordFromLine(nextLine, "dd/MM/yyyy");

                if (hotelDB.getDestination().equalsIgnoreCase(hotel.getDestination()) && hotelDB.getHotelCode().equalsIgnoreCase(hotel.getHotelCode()) && hotelDB.getRoomType().equalsIgnoreCase(hotel.getRoomType())) {
                    updateCSV(ResourceUtils.getFile("classpath:dbHotels.csv").getPath(), "SI", row, 7);

                    break;
                }

                row++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private static void updateCSV(String fileToUpdate, String replace, int row, int col) throws IOException {

        File inputFile = new File(fileToUpdate);

        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        List<String[]> csvBody = reader.readAll();

        csvBody.get(row)[col] = replace;
        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');

        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }


    public static List<FlightDTO> loadFlightDataBase(String dateFormat) throws IOException {
        List<FlightDTO> flights = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:dbFlights.csv").getPath()), ',');

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                flights.add(getFlightRecordFromLine(nextLine, dateFormat));
            }

            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return flights;
    }

    private static FlightDTO getFlightRecordFromLine(String[] nextLine, String dateFormat) {
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

    private static FlightDTO mapToFlightDTO(List<String> values, String dateFormat) throws ParseException {
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
