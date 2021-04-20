package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.services.DataBaseUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Repository
public class HotelRepositoryImpl implements HotelRepository {
    @Override
    public List<HotelDTO> findHotels(String dateFormat) throws IOException, ParseException {
        return DataBaseUtils.loadHotelDataBase(dateFormat);
    }
}
