package com.quizzetta.Validator;

import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.EmptyInputError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginValidator implements Validator {

    private final String userName;
    private final String password;
    private List<AppError> errors;
    //    private final UserDAO userDAO;
    private UniquenessValidator uniquenessValidator;
    private PasswordsMatchValidator passwordsMatchValidator;


    public LoginValidator(String userName, String password, UserDAO userDAO) {
        this.userName = userName;
        this.password = password;
//        this.userDAO = userDAO;
        this.uniquenessValidator = new UniquenessValidator(userName, userDAO);
        this.passwordsMatchValidator = new PasswordsMatchValidator(userName, password, userDAO);
    }


    @Override
    public boolean validate() {
        try {
            errors = new ArrayList<>();

            if (userName == null) { // TODO SHOULD WE PRINT ALL MISSING INFO, OR JUST THE FIRST ONE
                errors.add(new EmptyInputError("username", "Username Field has to be not empty"));
            } else if (password == null) {
                errors.add(new EmptyInputError("password", "Password Field has to be not empty"));
            }

            if (!passwordsMatchValidator.validate()) {
                errors.addAll(passwordsMatchValidator.getErrors());
            }
            return errors.size() == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<AppError> getErrors() {
        return errors;
    }
}
