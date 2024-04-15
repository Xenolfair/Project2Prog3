package org.projectBookstore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utilities {
    public static int calculateDaysDelay(String dateLoan, String dateDevolution) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateStart = LocalDate.parse(dateLoan, formatter);
        LocalDate dateFin = LocalDate.parse(dateDevolution, formatter);
        return (int) Math.max(0, dateFin.until(dateStart).getDays());
    }

    public static boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
