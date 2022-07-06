package com.nexai.project.validator;


import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.util.DateUtil;
import com.nexai.project.validation.PaymentValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

public class PaymentValidatorTest {
    private static final PaymentValidator VALIDATOR = new PaymentValidator();

    private static final DateUtil DATE_UTILS = new DateUtil();

    @ParameterizedTest
    @ValueSource(strings = {"3478593278541896", "5186287753828681", "6347070343774618", "1234432112344321"})
    void testIsCardNumberValidShouldReturnTrue(String cardNumber) {
        assertTrue(VALIDATOR.isCardNumberValid(cardNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456789", "invalidcardnumber", "6347O70343774618"})
    void testIsCardNumberValidShouldReturnFalse(String cardNumber) {
        assertFalse(VALIDATOR.isCardNumberValid(cardNumber));
    }


    @ParameterizedTest
    @ValueSource (strings = {"2025-07-13", "2056-06-12", "2022-12-30", "2021-05-12"})
    void testIsExpirationDateValidShouldReturnTrue(String s) throws InvalidDataException {
        LocalDate date = DATE_UTILS.parseDate(s);
        assertTrue(VALIDATOR.isExpirationDateValid(date));
    }

    @ParameterizedTest
    @ValueSource (strings = {"2005-07-13", "2006-06-12", "2012-12-30", "2020-04-10"})
    void testIsExpirationDateValidShouldReturnFalse(String s) throws InvalidDataException {
        LocalDate date = DATE_UTILS.parseDate(s);
        assertFalse(VALIDATOR.isExpirationDateValid(date));

    }
}
