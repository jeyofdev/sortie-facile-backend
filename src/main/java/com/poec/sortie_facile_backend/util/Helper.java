package com.poec.sortie_facile_backend.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Helper {
    /**
     * Format date to yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String simpleDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    /**
     * Capitalize the first letter of a string
     *
     * @return String
     */
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("The 'str' field must be provided and cannot be empty.");
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Capitalize each word of a string
     */
    public static String capitalizeEachWord(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("The 'str' field must be provided and cannot be empty.");
        }

        String[] words = str.split("[- ]");

        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            capitalized.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(str.contains("-") ? "-" : " ");
        }

        return capitalized.substring(0, capitalized.length() - 1);
    }

    /**
     * calculate age from a date
     */
    public static int calculateAge(LocalDate date) {
        LocalDate currentDate = LocalDate.now();

        if (date.isAfter(currentDate)) {
            throw new IllegalArgumentException("The date cannot be in the future.");
        }

        // period between date and current date
        Period period = Period.between(date, currentDate);

        return period.getYears();
    }

    /**
     * Convert a LocalDate to the format dd-MM-yyyy
     */
    public static String formatDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("The 'date' field must be provided.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    /**
     * Format phone number to XX XX XX XX XX
     */
    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("The 'phoneNumber' field must be provided and cannot be empty.");
        }

        return phoneNumber.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4 $5");
    }
}
