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

        validateInput(dateFrom, dateTo, destination, hotels);

        hotels = findHotelByDateFrom(hotels, dateFrom);
        hotels = findHotelByDateTo(hotels, dateTo);
        hotels = findHotelByDestination(hotels, destination);
        hotels = findHotelNotReserved(hotels);

        return hotels;
    }

    private List<HotelDTO> findHotelByDateFrom(List<HotelDTO> hotels, String dateFromStr) throws InvalidInputException, ParseException {
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

    private List<HotelDTO> findHotelByDateTo(List<HotelDTO> hotels, String dateToStr) throws InvalidInputException, ParseException {
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

    private List<HotelDTO> findHotelByDestination(List<HotelDTO> hotels, String destination) {
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

    private void validateInput(String dateFrom, String dateTo, String destination, List<HotelDTO> hotels) throws InvalidInputException, ParseException {
        if (dateFrom != null && !DateUtils.isValid(DATE_FORMAT, dateFrom))
            throw new InvalidInputException("The Date From format must be dd/mm/aaaa");

        if (dateTo != null && !DateUtils.isValid(DATE_FORMAT, dateTo))
            throw new InvalidInputException("The Date To format must be dd/mm/aaaa");

        if (dateFrom != null && dateTo != null && DateUtils.parse(DATE_FORMAT, dateFrom).toEpochDay() > DateUtils.parse(DATE_FORMAT, dateTo).toEpochDay())
            throw new InvalidInputException("Date From must be older than Date To");

        if (destination != null && !hotels.stream().anyMatch(h -> h.getDestination().equalsIgnoreCase(destination)))
            throw new InvalidInputException("The chosen destination does not exist");
    }
}
