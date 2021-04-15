package com.desafioQuality.desafioQuality.controllers;

import com.desafioQuality.desafioQuality.dtos.ErrorDTO;
import com.desafioQuality.desafioQuality.dtos.GeneralDTO;
import com.desafioQuality.desafioQuality.exceptions.GeneralException;
import com.desafioQuality.desafioQuality.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GeneralController {
    @Autowired
    private GeneralService generalService;

    @GetMapping("/articles")
    public ResponseEntity<List<GeneralDTO>> articles(@RequestParam(value = "productId", required = false) Long productId) throws GeneralException {
        List<GeneralDTO> articulos = generalService.findArticuloByFilters();

        return new ResponseEntity<>(articulos, HttpStatus.OK);
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<GeneralDTO> purchaseRequest(@RequestBody GeneralDTO purchaseRequest) throws GeneralException {
        GeneralDTO purchaseResponse = generalService.calculatePurchase();

        return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity exceptionHandler(GeneralException e) {
        return new ResponseEntity(new ErrorDTO(e.getTitle(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
