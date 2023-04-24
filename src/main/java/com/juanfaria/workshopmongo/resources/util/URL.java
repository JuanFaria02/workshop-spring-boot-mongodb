package com.juanfaria.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class URL {
    public static String decodeParam(String text){
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static LocalDateTime convertDate(String textDate, LocalDateTime defaultValue) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fmt.withZone(ZoneOffset.UTC);
        try {
        LocalDateTime date = LocalDate.parse(textDate, fmt).atStartOfDay();
        return date;
    }
      catch (DateTimeParseException e){
            return defaultValue;
        }
    }
}
