package com.quizzetta.Validator;

import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.EmptyInputError;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogInValidator implements Validator {

    private String userName;
    private String password;

    private List<AppError> errors;

    private UserExistsValidator userExists;

    public LogInValidator(String userName, String password, Connection connection) {
        this.userName = userName;
        this.password = password;

        this.userExists = new UserExistsValidator(userName, password, connection);

    }
    @Override
    public boolean validate() throws SQLException {
        errors = new ArrayList<>();

        if (userName == null) { // TODO SHOULD WE PRINT ALL MISSING INFO, OR JUST THE FIRST ONE
            errors.add(new EmptyInputError("username", "Username Field has to be not be empty"));
        } else if (password == null) {
            errors.add(new EmptyInputError("username", "Username Field has to be not be empty"));
        }

        UserNameValidator userNameValidator = new UserNameValidator(userName); // TODO we need to consider limitations

        if (!userNameValidator.validate()) {
            errors.addAll(userNameValidator.getErrors());
        }

        if (!userExists.validate()) {
            errors.addAll(userExists.getErrors()); // TODO check if user exists
        }

        return errors.size() == 0;
    }

    @Override
    public List<AppError> getErrors() {
        return errors;
    }
}
