package com.nexai.project.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentValidator extends Validator {

    private static final Pattern CONTENT_PATTERN = Pattern.compile(".{1,200}");

    public boolean isContentValid(String content) {
        Matcher matcher = CONTENT_PATTERN.matcher(content);
        boolean matches = matcher.matches();
        if (!matches) {
            message = "Invalid content";
        }
        return matches;
    }
}
