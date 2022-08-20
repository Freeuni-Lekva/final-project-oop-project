package com.quizzetta.Validator;

import com.quizzetta.Errors.AppError;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterValidator implements Validator{

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final Connection connection;

    private List<AppError> errors;

    public RegisterValidator(String username, String firstName, String lastName, String password, Connection dataBaseConn) {
        this.username = username;
        
        this.firstName = firstName;
        this.lastName = lastName;

        this.password = password;
        this.connection = dataBaseConn;

        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() throws SQLException {
        return false; // TODO NEEDS TO BE IMPLEMENTED
    }

    @Override
    public List<AppError> getErrors() {
        return errors;
    }
}
