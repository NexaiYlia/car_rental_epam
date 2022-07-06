package com.nexai.project.validation;


import com.nexai.project.util.DateUtil;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class PaymentValidator extends Validator {

    private static final Pattern CARD_NUMBER_REGEX = Pattern.compile("\\d{16}");
    private static final Pattern CVV_NUMBER_REGEX = Pattern.compile("\\d{3}");

    private static final DateUtil DATE_UTILS = new DateUtil();

    public boolean isCardNumberValid(String cardNumber) {
        boolean isMatch = CARD_NUMBER_REGEX.matcher(cardNumber).matches();
        if (!isMatch) {
            message = "Invalid card number";
        }
        return isMatch;
    }

    public boolean isExpirationDateValid(LocalDate expirationDate) {
        boolean isValid = expirationDate.isAfter(LocalDate.now());
        if (!isValid) {
            message = "Invalid expiry date";
        }
        return isValid;
    }

    public boolean isCvvNumberValid(String cvvNumber) {
        boolean isMatch = CVV_NUMBER_REGEX.matcher(cvvNumber).matches();
        if (!isMatch) {
            message = "Invalid cvv";
        }
        return isMatch;
    }
}
