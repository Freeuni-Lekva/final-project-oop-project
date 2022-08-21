package com.quizzetta.Validator;

import com.quizzetta.Errors.AppError;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserExistsValidator implements Validator {
    private final String userName;
    private final String password;
    private final Connection connection;

    public UserExistsValidator(String userName, String password, Connection connection) {
        this.userName = userName;
        this.password = password;
        this.connection = connection;
    }

    @Override
    public boolean validate() throws SQLException {
        return false;
    }

    @Override
    public List<AppError> getErrors() {
        return null;
    }
}
