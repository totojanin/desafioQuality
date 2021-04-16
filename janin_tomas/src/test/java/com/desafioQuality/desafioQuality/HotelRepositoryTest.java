package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.dtos.HotelDTO;
import com.desafioQuality.desafioQuality.repositories.HotelRepository;
import com.desafioQuality.desafioQuality.repositories.HotelRepositoryImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

@WebMvcTest(HotelRepository.class)
public class HotelRepositoryTest {
    private HotelRepository hotelRepository;

    @BeforeEach
    void setUp() {
        hotelRepository = new HotelRepositoryImpl();
    }

    @Test
    @DisplayName("Gets all the hotels in the repository")
    public void getAllHotels() throws Exception {
        List<HotelDTO> responseHotels = hotelRepository.findHotels("dd/MM/yyyy");

        Assertions.assertEquals(12, responseHotels.size());
    }
}
