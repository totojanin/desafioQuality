package com.desafioQuality.desafioQuality.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    public static boolean isValid(String dateFormat, String date) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);

        sdf.setLenient(false);

        try {
            sdf.parse(date);
        }
        catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static LocalDate parse(String dateFormat, String date) throws ParseException {
        DateFormat sdf = new SimpleDateFormat(dateFormat);

        sdf.setLenient(false);

        return sdf.parse(date).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
