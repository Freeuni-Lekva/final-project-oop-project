package com.quizzetta.Validator;

import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.EmptyInputError;

import javax.management.relation.RelationSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniquenessValidator implements Validator {
    private final long id;
    private final String username;
    private final Connection connection;

    private List<AppError> errors;

    public UniquenessValidator(long id, String username, Connection connection) {
        if (username == null) {
            throw new IllegalArgumentException("Username has to be be filled");
        }

        this.id = id;
        this.username = username;
        this.connection = connection;

        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() throws SQLException {
        if (username != null) {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM userTable where nickname = ?");
            stm.setString(1, username);

            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                errors.add(new EmptyInputError("username","username is already taken"));
            }
        }

        return errors.size() == 0;
    }

    @Override
    public List<AppError> getErrors() {
        return errors;
    }
}
