package com.nexai.project.validator;


import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.util.DateUtil;
import com.nexai.project.validation.PassportValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class UserPassportDataValidatorTest {
    private static final PassportValidator VALIDATOR = new PassportValidator();
    private static final DateUtil DATE_UTILS = new DateUtil();

    @ParameterizedTest
    @ValueSource(strings = {"ВМ3229423", "BM2496612", "АА5434944", "AK0001122"})
    void testIsValidPassportNumberShouldReturnTrue(String passportNumber) {
        assertTrue(VALIDATOR.isValidPassportNumber(passportNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3229423ВМ", "ВИИ233992", "В12345678"})
    void testIsValidPassportNumberShouldReturnFalse(String passportNumber) {
        assertFalse(VALIDATOR.isValidPassportNumber(passportNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"7244321А321ИВ3", "5674321А321ИВ9", "8429135А538РВ9", "9135930Е853РВ8",
            "4354221А321ИВ3", "5395821Е693РВ6"})
    void testIsValidIdentificationNumberShouldReturnTrue(String identificationNumber) {
        assertTrue(VALIDATOR.isValidIdentificationNumber(identificationNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678910", "идентификация", "72443212321ИВ3", "7244321А321423"})
    void testIsValidIdentificationNumberShouldReturnFalse(String identificationNumber) {
        assertFalse(VALIDATOR.isValidIdentificationNumber(identificationNumber));
    }

    // Result also depends on current date
    @ParameterizedTest
    @ValueSource (strings = {"2025-07-13", "2056-06-12", "2022-12-30", "2022-05-12"})
    void testIsValidIssueDateShouldReturnTrue(String s) throws InvalidDataException {
        LocalDate date = DATE_UTILS.parseDate(s);
        assertTrue(VALIDATOR.isValidIssueDate(date));
    }

    @ParameterizedTest
    @ValueSource (strings = {"2005-07-13", "2006-06-12", "2012-12-30", "2020-04-10"})
    void testIsValidIssueDateShouldReturnFalse(String s) throws InvalidDataException {
        LocalDate date = DATE_UTILS.parseDate(s);
        assertFalse(VALIDATOR.isValidIssueDate(date));
    }
}
