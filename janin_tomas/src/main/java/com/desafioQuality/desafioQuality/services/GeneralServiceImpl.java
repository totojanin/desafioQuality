package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.GeneralDTO;
import com.desafioQuality.desafioQuality.exceptions.GeneralException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralServiceImpl implements GeneralService {
    @Override
    public List<GeneralDTO> findArticuloByFilters() throws GeneralException {
        return null;
    }

    @Override
    public GeneralDTO calculatePurchase() throws GeneralException {
        return null;
    }
}
