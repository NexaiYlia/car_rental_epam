package com.nexai.project.validator;

import com.nexai.project.validation.UserValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
public class UserValidatorTest {
    private static final UserValidator VALIDATOR = new UserValidator();

    @ParameterizedTest
    @ValueSource(strings = {"validemail@gmail.com", "simplemail@mail.ru", "oldman1957@email.com",
            "example-indeed@example.com", "very.common@example.com"})
    void isValidEmailShouldReturnTrue(String email) {
        assertTrue(VALIDATOR.isValidEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Abc.example.com", "A@b@c@example.com", "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com",
            "just\"not\"right@example.com", "this is\"not\\allowed@example.com ",
            "i_like_underscore@but_its_not_allowed_in_this_part.example.com",
            "1234567890123456789012345678901234567890123456789012345678901234+x@example.com"})
    void isValidEmailShouldReturnFalse(String email) {
        assertFalse(VALIDATOR.isValidEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678", "strongpassword", "pa33w0rdw1thd1g1t3", "1a2d3f4g5h6j7k"})
    void isValidPasswordShouldReturnTrue(String password){
        assertTrue(VALIDATOR.isValidPassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567", "weakpsw", "                           ",
            "I love space", "_____________"})
    void isValidPasswordShouldReturnFalse(String password){
        assertFalse(VALIDATOR.isValidPassword(password));
    }
}
