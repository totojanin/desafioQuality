package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.GeneralDTO;
import com.desafioQuality.desafioQuality.exceptions.GeneralException;

import java.util.List;

public interface GeneralService {
    List<GeneralDTO> findArticuloByFilters() throws GeneralException;
    GeneralDTO calculatePurchase() throws GeneralException;
}
