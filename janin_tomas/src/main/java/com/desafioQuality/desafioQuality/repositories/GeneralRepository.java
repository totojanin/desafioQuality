package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.GeneralDTO;

import java.io.IOException;
import java.util.List;

public interface GeneralRepository {
    List<GeneralDTO> findArticulos() throws IOException;
}
