package com.nexai.project.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator extends Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[^$@!\"']{4,20}@\\w+\\.\\w+");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(.*[A-Za-z0-9])[A-Za-z\\d]{6,}$");

    public boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        boolean isValid = matcher.matches();
        if (!isValid) {
            message = "Invalid user's email";
        }
        return isValid;
    }

    public boolean isValidPassword(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        boolean isValid = matcher.matches();
        if (!isValid) {
            message = "Invalid user's password";
        }
        return isValid;
    }
}
