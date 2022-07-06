package com.nexai.project.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDetailValidator extends Validator {

    private static final Pattern NAME_PATTERN = Pattern.compile("[A-ZА-Я][a-zа-я]*");
    private static final Pattern PHONE_PATTERN = Pattern.compile("[+](\\d{12})");

    public boolean isValidName(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        if (!matcher.matches()) {
            message = "Invalid name";
            return false;
        }
        return true;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        if (!matcher.matches()) {
            message = "Invalid phone number";
            return false;
        }
        return true;
    }
}
