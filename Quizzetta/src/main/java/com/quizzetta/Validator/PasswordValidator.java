package com.quizzetta.Validator;

import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.EmptyInputError;
import com.quizzetta.Errors.ValidationError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator implements Validator {
    public static final int PASSWORD_MIN_LENGTH = 8;

    public static final String PASSWORD_LENGTH_ERROR = "Password has to be contain at least 8 characters";
    public static final String PASSWORD_UPPERCASE_ERROR = "Password has to be contain at least 1 upper case character";
    public static final String PASSWORD_LOWERCASE_ERROR = "Password has to be contain at least 1 lower case character";
    public static final String PASSWORD_DIGIT_ERROR = "Password has to be contain at least 1 digit";


    private String password;
    private List<ValidationError> errors;

    public PasswordValidator(String password) {
        this.password = password;
        errors = new ArrayList<>();
    }


    // TODO PasswordValidator should not be using EmptyInputErrors.
    @Override
    public boolean validate() throws SQLException {
        if (password.length() < PASSWORD_MIN_LENGTH) {
            errors.add(new ValidationError(PASSWORD_LENGTH_ERROR));
        }

        Pattern upperCaseValuesPattern = Pattern.compile("[A-Z]");
        Pattern lowerCaseValuesPattern = Pattern.compile("[a-z]");
        Pattern digitValuesPattern = Pattern.compile("[0-9]");

        if (!upperCaseValuesPattern.matcher(password).find()) {
            errors.add(new ValidationError(PASSWORD_UPPERCASE_ERROR));
        }

        if (!lowerCaseValuesPattern.matcher(password).find()) {
            errors.add(new ValidationError(PASSWORD_LOWERCASE_ERROR));
        }

        if (!digitValuesPattern.matcher(password).find()) {
            errors.add(new ValidationError(PASSWORD_DIGIT_ERROR));
        }

        return errors.size() == 0;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
