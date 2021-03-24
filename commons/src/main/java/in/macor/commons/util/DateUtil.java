/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.commons.util;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author macorin
 */
public class DateUtil {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static String formatDate(LocalDate date) {
        return date == null ? null : date.toString(DATE_FORMAT);
    }

    public static LocalDate parseDate(String date) {
        if (date != null && !date.trim().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
            return formatter.parseLocalDate(date);
        }
        return null;
    }

}
