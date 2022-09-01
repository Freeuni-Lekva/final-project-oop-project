package com.quizzetta.Validator;

import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.EmptyInputError;
import com.quizzetta.Errors.UserNotFoundError;
import com.quizzetta.Model.User;

import javax.management.relation.RelationSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniquenessValidator implements Validator {
    //    private final long id;
    private final String username;
    //    private final Connection connection;
    UserDAO userDAO;


    private List<AppError> errors;

    public UniquenessValidator(String username, UserDAO userDAO) {
//        if (username == null) {
//            throw new IllegalArgumentException("Username has to be be filled");
//        }

//        this.id = id;
        this.username = username;
//        this.connection = connection;
        this.userDAO = userDAO;
        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() {
        System.out.println("SHEMOSVLA VALIDATESHI");
        try {
            if (userDAO.getUser(username) != null) {
                errors.add(new EmptyInputError("username", "The username is already taken"));
            }
            return errors.size() == 0;
        } catch (SQLException e) {
            return true;
        }

//
//        if (username != null) {
//            System.out.println("SHEMOSVLA IF STATEMENTSHI");
//            PreparedStatement stm = connection.prepareStatement("SELECT * FROM userTable where nickname = ?");
//            stm.setString(1, username);
//
//            System.out.println("ABA AQAMDE TU MOVALT ABA MAGREBI VART");
//            ResultSet resultSet = stm.executeQuery();
//            if (resultSet.next()) {
//                errors.add(new EmptyInputError("username","username is already taken"));
//            }
//        }

    }

    @Override
    public List<AppError> getErrors() {
        return errors;
    }
}
