package com.quizzetta.Validator;

import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.UserNotFoundError;
import com.quizzetta.Errors.WrongPasswordError;
import com.quizzetta.Hasher;
import com.quizzetta.Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordsMatchValidator implements Validator {
    private final String userName;
    private final String password;
    private final UserDAO userDAO;
    private List<AppError> errors;

    public PasswordsMatchValidator(String userName, String password, UserDAO userDAO) {
        this.userName = userName;
        this.password = password;
        this.userDAO = userDAO;
        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() {
        User user = null;
        try {
            user = userDAO.getUser(userName);
            String passwordHashed = Hasher.generateHash(password);
            if (user.getPasswordHash().equals(passwordHashed)) {
                return true;
            }
            errors.add(new WrongPasswordError("password", "The passwords do not match!"));
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            errors.add(new UserNotFoundError("password", "The Username is not in found"));
            return false;
        }
    }

    @Override
    public List<AppError> getErrors() {
        return errors;
    }
}
