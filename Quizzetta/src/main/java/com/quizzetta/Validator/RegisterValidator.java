package com.quizzetta.Validator;

import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.EmptyInputError;
import com.sun.tools.javac.util.Pair;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

    private boolean isEntirelyFilled() {
        List<Pair> pairs = Arrays.asList(
                new Pair("username", username),
                new Pair("firstName", firstName),
                new Pair("lastName", lastName),
                new Pair("password", password)
        );

        for (Pair pair: pairs) {
            String key = (String) pair.fst;
            Object value = pair.snd;

            if (value == null) {
                String keyText = String.valueOf(key.charAt(0)).toUpperCase() + key.substring(1);
                errors.add(new EmptyInputError(key, keyText + " has to be be inputed"));
            }
        }

        return errors.size() == 0;
    }

    private boolean isCorrectFormatForPassword() throws SQLException {
        PasswordValidator passwordValidator = new PasswordValidator(this.password);

        if (!passwordValidator.validate()) {
            errors.addAll(passwordValidator.getErrors());
        }

        return errors.size() == 0;
    } // TODO SHOULD WE ADD SECOND INPUT OF A PASSWORD "CONFIRM PASSWORD"

    private boolean isUniqueUserName() throws SQLException {
        UniquenessValidator uniquenessValidator = new UniquenessValidator(-1, username, connection);

        if (!uniquenessValidator.validate()) {
            errors.addAll(uniquenessValidator.getErrors());
        }

        return errors.size() == 0;
    }

    @Override
    public boolean validate() throws SQLException {
        return isEntirelyFilled() && isUniqueUserName() && isCorrectFormatForPassword();
    } // TODO SHOULD WE CHECK ALL THE CASES OR RETURN ON THE FIRST FALSE


    @Override
    public List<AppError> getErrors() {
        return errors;
    }
}
