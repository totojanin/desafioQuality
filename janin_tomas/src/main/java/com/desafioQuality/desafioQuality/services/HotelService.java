package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.exceptions.InvalidInputException;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public interface HotelService {
    List<HotelDTO> findHotelsByFilters(String dateFrom, String dateTo, String destination) throws InvalidInputException, IOException, ParseException;
    List<HotelDTO> findHotelByDateFrom(List<HotelDTO> hotels, String dateFromStr) throws InvalidInputException, ParseException;
    List<HotelDTO> findHotelByDateTo(List<HotelDTO> hotels, String dateToStr) throws InvalidInputException, ParseException;
    List<HotelDTO> findHotelByDestination(List<HotelDTO> hotels, String destination);
    List<HotelDTO> findHotelByHotelCode(List<HotelDTO> hotels, String hotelCode);
    List<HotelDTO> findHotelByRoomType(List<HotelDTO> hotels, String roomType);
}
