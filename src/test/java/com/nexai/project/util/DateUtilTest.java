package com.nexai.project.util;

import com.nexai.project.exception.InvalidDataException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.time.LocalDate;
import java.util.Date;

import static org.testng.AssertJUnit.*;


public class DateUtilTest {
    private static final DateUtil DATE_UTILS = new DateUtil();

    @ParameterizedTest
    @CsvSource({"2021-04-11,2021-04-14,3", "2021-04-28,2021-04-11,17", "2021-01-01,2022-01-01,365"})
    void testCalculateDaysBetweenDatesShouldReturnExpectedDaysAmount(String date1, String date2, int expected)
            throws InvalidDataException {
        LocalDate firstDate = DATE_UTILS.parseDate(date1);
        LocalDate secondDate = DATE_UTILS.parseDate(date2);
        assertEquals(DATE_UTILS.calculateDaysBetweenDates(firstDate, secondDate), expected);
    }

    @ParameterizedTest
    @CsvSource({"2021-04-15,2021-04-11,2021-04-26", "2021-04-15,2021-04-15,2021-04-26", "2021-04-26,2021-04-11,2021-04-26"})
    void isBetweenDatesShouldReturnTrue(String date1, String date2, String date3) throws InvalidDataException {
        LocalDate date = DATE_UTILS.parseDate(date1);
        LocalDate startDate = DATE_UTILS.parseDate(date2);
        LocalDate endDate = DATE_UTILS.parseDate(date3);
        DATE_UTILS.isBetweenDates(date, startDate, endDate);
        assertTrue(DATE_UTILS.isBetweenDates(date, startDate, endDate));
    }

    @ParameterizedTest
    @CsvSource({"2021-04-15,2021-04-11,2021-04-26", "2021-04-15,2021-04-15,2021-04-26", "2021-04-26,2021-04-11,2021-04-26"})
    void isBetweenDatesShouldReturnFalse(String date1, String date2, String date3) throws InvalidDataException {
        LocalDate date = DATE_UTILS.parseDate(date1);
        LocalDate startDate = DATE_UTILS.parseDate(date2);
        LocalDate endDate = DATE_UTILS.parseDate(date3);
        DATE_UTILS.isBetweenDates(date, startDate, endDate);
        assertFalse(DATE_UTILS.isBetweenDates(date, startDate, endDate));
    }
}
