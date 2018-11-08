package ru.kracoz.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateTimeHelper {

    public static LocalDateTime parseDateTime(String dateTime, String format) {
        return parseDateTime(dateTime, format, Locale.forLanguageTag("RU"));
    }

    public static LocalDateTime parseDateTime(String dateTime, String format, Locale locale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format, locale);
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException ex) {
            throw new Error("Не получилось распарсить дату " + dateTime, ex);
        }
    }
}
