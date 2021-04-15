package com.desafioQuality.desafioQuality.services;

import com.desafioQuality.desafioQuality.dtos.GeneralDTO;
import com.opencsv.CSVReader;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralTestUtils {
    public static List<GeneralDTO> getArticulos() throws IOException {
        return loadDataBase();
    }

    private static List<GeneralDTO> loadDataBase() throws IOException {
        List<GeneralDTO> articulos = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:dbTest.csv").getPath()), ',');

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

    private static GeneralDTO getRecordFromLine(String[] nextLine) {
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

    private static GeneralDTO mapToArticuloDTO(List<String> values) {
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

    public static GeneralDTO getArticulo1() {
        return new GeneralDTO(1L, "Remera");
    }

    public static GeneralDTO getArticulo2() {
        return new GeneralDTO(2L, "Martillo");
    }

    public static GeneralDTO getArticulo3() {
        return new GeneralDTO(3L, "Notebook");
    }

    public static GeneralDTO getArticulo4() {
        return new GeneralDTO(4L, "Moto G9");
    }
}
