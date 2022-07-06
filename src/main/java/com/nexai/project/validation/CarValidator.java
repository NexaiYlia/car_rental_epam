package com.nexai.project.validation;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarValidator extends Validator {

    private static final Pattern YEAR_PATTERN = Pattern.compile("19|(20)[\\d][\\d]");


    public boolean isYearValid(String year) {
        Matcher matcher = YEAR_PATTERN.matcher(year);
        if (!matcher.matches()) {
            message = "Invalid manufactured year";
            return false;
        }
        return true;
    }


    public boolean isPriceValid(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            message = "Invalid price";
            return false;
        }
        return true;
    }
}
