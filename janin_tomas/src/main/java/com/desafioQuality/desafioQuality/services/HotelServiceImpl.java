package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.FlightDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;
import com.desafioQuality.desafioQuality.repositories.HotelRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private final String DATE_FORMAT = "dd/MM/yyyy";

    private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelDTO> findHotelsByFilters(String dateFrom, String dateTo, String destination) throws InvalidInputException, IOException, ParseException {
        List<HotelDTO> hotels = hotelRepository.findHotels(DATE_FORMAT);

        ValidationUtils.validateInputHotels(dateFrom, dateTo, destination, hotels);

        hotels = findHotelByDateFrom(hotels, dateFrom);
        hotels = findHotelByDateTo(hotels, dateTo);
        hotels = findHotelByDestination(hotels, destination);
        hotels = findHotelNotReserved(hotels);

        return hotels;
    }

    public List<HotelDTO> findHotelByDateFrom(List<HotelDTO> hotels, String dateFromStr) throws InvalidInputException, ParseException {
        LocalDate dateFrom;

        if (dateFromStr == null)
            return hotels;
        else {
            dateFrom = DateUtils.parse(DATE_FORMAT, dateFromStr);
        }

        List<HotelDTO> response = hotels.stream()
                .filter(h -> h.getAvailableFrom().toEpochDay() <= dateFrom.toEpochDay() && h.getAvailableTo().toEpochDay() >= dateFrom.toEpochDay())
                .collect(Collectors.toList());

        if (!response.isEmpty())
            return response;
        else
            throw new InvalidInputException("There are no hotels with the given input");
    }

    public List<HotelDTO> findHotelByDateTo(List<HotelDTO> hotels, String dateToStr) throws InvalidInputException, ParseException {
        LocalDate dateTo;

        if (dateToStr == null)
            return hotels;
        else {
            dateTo = DateUtils.parse(DATE_FORMAT, dateToStr);
        }

        List<HotelDTO> response = hotels.stream()
                .filter(h -> h.getAvailableFrom().toEpochDay() <= dateTo.toEpochDay() && h.getAvailableTo().toEpochDay() >= dateTo.toEpochDay())
                .collect(Collectors.toList());

        if (!response.isEmpty())
            return response;
        else
            throw new InvalidInputException("There are no hotels with the given input");
    }

    public List<HotelDTO> findHotelByDestination(List<HotelDTO> hotels, String destination) {
        if (destination == null)
            return hotels;

        List<HotelDTO> response = hotels.stream()
                .filter(h -> h.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());

        return response;
    }

    private List<HotelDTO> findHotelNotReserved(List<HotelDTO> hotels) throws InvalidInputException {
        List<HotelDTO> response = hotels.stream()
                .filter(h -> h.getReserved().equalsIgnoreCase("NO"))
                .collect(Collectors.toList());

        if (!response.isEmpty())
            return response;
        else
            throw new InvalidInputException("There are no hotels available");
    }

    public List<HotelDTO> findHotelByHotelCode(List<HotelDTO> hotels, String hotelCode) {
        if (hotelCode == null)
            return hotels;

        List<HotelDTO> response = hotels.stream()
                .filter(h -> h.getHotelCode().equalsIgnoreCase(hotelCode))
                .collect(Collectors.toList());

        return response;
    }

    public List<HotelDTO> findHotelByRoomType(List<HotelDTO> hotels, String roomType) {
        if (roomType == null)
            return hotels;

        List<HotelDTO> response = hotels.stream()
                .filter(h -> h.getRoomType().equalsIgnoreCase(roomType))
                .collect(Collectors.toList());

        return response;
    }
}
