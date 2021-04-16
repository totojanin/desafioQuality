package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.HotelDTO;
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
public class HotelRepositoryImpl implements HotelRepository {
    @Override
    public List<HotelDTO> findHotels(String dateFormat) throws IOException {
        List<HotelDTO> db = loadDataBase(dateFormat);

        List<HotelDTO> hotels = new ArrayList<>();

        if (db != null) {
            hotels = db;
        }

        return hotels;
    }

    private List<HotelDTO> loadDataBase(String dateFormat) throws IOException {
        List<HotelDTO> hotels = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:dbHotels.csv").getPath()), ',');

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                hotels.add(getRecordFromLine(nextLine, dateFormat));
            }

            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return hotels;
    }

    private HotelDTO getRecordFromLine(String[] nextLine, String dateFormat) {
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

    private HotelDTO mapToHotelDTO(List<String> values, String dateFormat) throws ParseException {
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
}
