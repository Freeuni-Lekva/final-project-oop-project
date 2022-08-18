package Validator;

import Errors.AppError;

import java.sql.SQLException;
import java.util.List;

public interface Validator {
    boolean validate() throws SQLException;
    List<AppError> getErrors();
}
