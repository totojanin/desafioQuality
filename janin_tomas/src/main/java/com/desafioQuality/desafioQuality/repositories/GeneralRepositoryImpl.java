package com.desafioQuality.desafioQuality.repositories;

import com.desafioQuality.desafioQuality.dtos.GeneralDTO;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GeneralRepositoryImpl implements GeneralRepository {
    @Override
    public List<GeneralDTO> findArticulos() throws IOException {
        List<GeneralDTO> db = loadDataBase();

        List<GeneralDTO> articulos = new ArrayList<>();

        if (db != null) {
            articulos = db;
        }

        return articulos;
    }

    private List<GeneralDTO> loadDataBase() throws IOException {
        List<GeneralDTO> articulos = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader("/src/main/resources/dbGeneral.csv"), ',');

            String[] nextLine = reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                articulos.add(getRecordFromLine(nextLine));
            }

            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return articulos;
    }

    private GeneralDTO getRecordFromLine(String[] nextLine) {
        GeneralDTO articulo = new GeneralDTO();

        try {
            List<String> lineAsList = new ArrayList<String>(Arrays.asList(nextLine));

            articulo = mapToArticuloDTO(lineAsList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return articulo;
    }

    private GeneralDTO mapToArticuloDTO(List<String> values) {
        long productId = Long.parseLong(values.get(0));
        String name = values.get(1);
        String category = values.get(2);
        String brand = values.get(3);
        double price = Double.parseDouble(values.get(4).replace("$", "").replace(".", ""));
        int quantity = Integer.parseInt(values.get(5));
        boolean freeShipping = values.get(6).equals("SI");
        String prestige = values.get(7);

        return new GeneralDTO(productId, name);
    }
}
