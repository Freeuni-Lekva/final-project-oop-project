package com.quizzetta.Validator;

import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.ValidationError;

import java.sql.SQLException;
import java.util.List;

public interface Validator {
    boolean validate() throws SQLException;
    List<ValidationError> getErrors();
}
