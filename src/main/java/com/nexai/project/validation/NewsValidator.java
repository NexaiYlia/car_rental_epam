package com.nexai.project.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsValidator extends Validator {
    private static final Pattern HEADER_PATTERN = Pattern.compile("(^[A-ZА-Я].+){1,100}$");
    private static final Pattern CONTENT_PATTERN = Pattern.compile("(^[A-ZА-Я<].+){1,255}$");

    public boolean isValidHeader(String header) {
        Matcher matcher = HEADER_PATTERN.matcher(header);
        boolean isValid = matcher.matches();
        if (!isValid) {
            message = "Invalid header data: " + header;
        }
        return isValid;
    }

    public boolean isValidContent(String content) {
        Matcher matcher = CONTENT_PATTERN.matcher(content);
        boolean isValid = matcher.matches();
        if (!isValid) {
            message = "Invalid content data: " + content;
        }
        return isValid;
    }
}
