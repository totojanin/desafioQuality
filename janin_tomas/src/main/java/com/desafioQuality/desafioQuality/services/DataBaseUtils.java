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
    public static List<HotelDTO> loadHotelDataBase(String dateFormat) throws IOException, ParseException {
        List<HotelDTO> db = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:dbHotels.csv").getPath()), ',');

        String[] nextLine = reader.readNext();

        while ((nextLine = reader.readNext()) != null) {
            db.add(getHotelRecordFromLine(nextLine, dateFormat));
        }

        reader.close();

        List<HotelDTO> hotels = new ArrayList<>();

        if (db != null) {
            hotels = db;
        }

        return hotels;
    }

    private static HotelDTO getHotelRecordFromLine(String[] nextLine, String dateFormat) throws ParseException {
        HotelDTO hotel = new HotelDTO();

        List<String> lineAsList = new ArrayList<String>(Arrays.asList(nextLine));

        hotel = mapToHotelDTO(lineAsList, dateFormat);

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
        catch (IOException | ParseException e) {
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

    public static void resetReservation() throws IOException {

        File inputFile = new File(ResourceUtils.getFile("classpath:dbHotels.csv").getPath());

        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        List<String[]> csvBody = reader.readAll();

        csvBody.get(7)[7] = "NO";
        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');

        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }


    public static List<FlightDTO> loadFlightDataBase(String dateFormat) throws IOException, ParseException {
        List<FlightDTO> flights = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:dbFlights.csv").getPath()), ',');

        String[] nextLine = reader.readNext();

        while ((nextLine = reader.readNext()) != null) {
            flights.add(getFlightRecordFromLine(nextLine, dateFormat));
        }

        reader.close();

        return flights;
    }

    private static FlightDTO getFlightRecordFromLine(String[] nextLine, String dateFormat) throws ParseException {
        FlightDTO flight = new FlightDTO();

        List<String> lineAsList = new ArrayList<String>(Arrays.asList(nextLine));

        flight = mapToFlightDTO(lineAsList, dateFormat);

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
