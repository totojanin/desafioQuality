package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.BookingRequestDTO;
import com.desafioQuality.desafioQuality.dtos.HotelDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface HotelRepository {
    List<HotelDTO> findHotels(String dateFormat) throws IOException;
}
