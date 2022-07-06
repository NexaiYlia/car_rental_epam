package com.nexai.project.util;

import com.nexai.project.exception.InvalidDataException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public int calculateDaysBetweenDates(LocalDate startDay, LocalDate endDay) {
        Period period = Period.between(startDay, endDay);
        return period.getDays();
    }

    public Date getCurrentDateWithoutTime() {
        Calendar calendar = Calendar.getInstance();
        resetTime(calendar);
        return calendar.getTime();
    }

    public String formatDate(LocalDate date) {
        return date.toString();
    }

    public boolean isBetweenDates(LocalDate date, LocalDate start, LocalDate end) {
        return date.isAfter(start) && date.isBefore(end) || date.equals(start) || date.equals(end);
    }

    private void resetTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public LocalDate parseDate(String date)  {
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
