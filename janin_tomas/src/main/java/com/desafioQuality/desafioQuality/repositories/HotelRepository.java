package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.HotelDTO;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface HotelRepository {
    List<HotelDTO> findHotels(String dateFormat) throws IOException, ParseException;
}
