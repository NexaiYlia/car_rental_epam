package com.nexai.project.validation;

import com.nexai.project.util.DateUtil;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportValidator extends Validator {

    private static final DateUtil DATE_UTILS = new DateUtil();

    private static final Pattern PASSPORT_NUMBER_PATTERN = Pattern.compile("([A-ZА-Я]{2})([\\d]{7})");
    private static final Pattern IDENTIFICATION_NUMBER_PATTERN = Pattern.compile("([\\d]{7})[A-ZА-Я]([\\d]{3})[A-ZА-Я][A-ZА-Я][\\d]");
    private static final Pattern ISSUE_DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public boolean isValidPassportNumber(String passportNumber) {
        Matcher matcher = PASSPORT_NUMBER_PATTERN.matcher(passportNumber);
        boolean isValid = matcher.matches();
        if (!isValid) {
            message = "Invalid passport number";
        }
        return isValid;
    }

    public boolean isValidIdentificationNumber(String identificationNumber) {
        Matcher matcher = IDENTIFICATION_NUMBER_PATTERN.matcher(identificationNumber);
        boolean isValid = matcher.matches();
        if (!isValid) {
            message = "Invalid identification number";
        }
        return isValid;
    }

    public boolean isValidIssueDate(LocalDate issueDate) {
        Matcher matcher = ISSUE_DATE_PATTERN.matcher(DATE_UTILS.formatDate(issueDate));
        boolean isValid = matcher.matches();
        if (!isValid || issueDate.isBefore(LocalDate.now())) {
            message = "Invalid issue date";
            isValid = false;
        }
        return isValid;
    }
}
