package com.quizzetta.Validator;

import com.quizzetta.DAOs.UserDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.EmptyInputError;
import com.quizzetta.Errors.ValidationError;
import com.quizzetta.HelperClasses.Pair;
import com.quizzetta.Model.User;
//import com.sun.tools.javac.util.Pair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterValidator implements Validator {

    private final String email;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final UserDAO userDAO;
//    private final Connection connection;

    private List<ValidationError> errors;

    public RegisterValidator(String email, String username, String firstName, String lastName, String password, UserDAO userDAO) {
        this.email = email;
        this.username = username;

        this.firstName = firstName;
        this.lastName = lastName;

        this.password = password;

//        this.connection = dataBaseConn;
        this.userDAO = userDAO;

        this.errors = new ArrayList<>();
    }

    private boolean isEntirelyFilled() {
        System.out.println("SHEMOSVLA FILLEDSHIIII");
        List<Pair<String, String>> pairs = Arrays.asList(
                new Pair<>("email", email),
                new Pair<>("username", username),
                new Pair<>("firstName", firstName),
                new Pair<>("lastName", lastName),
                new Pair<>("password", password)
        );

        for (Pair pair : pairs) {
//            System.out.println("PAIR: " + pair);
            String key = (String) pair.getFirst();
            Object value = pair.getSecond();

            if (value == null) {
                String keyText = String.valueOf(key.charAt(0)).toUpperCase() + key.substring(1);
                errors.add(new ValidationError(keyText + " has to be be inputed"));
            }
        }
        System.out.println("IS ENTIRELY FILLED PASSED");
        return errors.size() == 0;
    }

    private boolean isUniqueUserName() throws SQLException {
        UniquenessValidator uniquenessValidator = new UniquenessValidator(username, userDAO);
        System.out.println("AGE AQ VART");
        if (!uniquenessValidator.validate()) {
            System.out.println("AR ARIS UNIQUE");
            errors.addAll(uniquenessValidator.getErrors());
        }
        System.out.println("UNIQUEA");
        return errors.size() == 0;
    }

    private boolean isCorrectFormatForPassword() throws SQLException {
        PasswordValidator passwordValidator = new PasswordValidator(this.password);

        if (!passwordValidator.validate()) {
            System.out.println("INCORRECT FORMAT");
            errors.addAll(passwordValidator.getErrors());
        }

        System.out.println("CORRECT " + "FORMAT");
        return errors.size() == 0;
    } // TODO SHOULD WE ADD SECOND INPUT OF A PASSWORD "CONFIRM PASSWORD"


    @Override
    public boolean validate() {
//        try {
//            System.out.println("SHEMOSVLA VALIDATESHI");
//            boolean b1 = isEntirelyFilled();
//            boolean b2 = isUniqueUserName();
//            boolean b3 = isCorrectFormatForPassword();
//            return b1 & b2 & b3;
//        } catch (SQLException e) {
//            System.out.println("SQL EXCEPTION: ");
//            e.printStackTrace();
//        }
//        return false;
        try {
            return isEntirelyFilled() && isUniqueUserName() && isCorrectFormatForPassword();
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION: ");
            e.printStackTrace();
        }
        return false;
    } // TODO SHOULD WE CHECK ALL THE CASES OR RETURN ON THE FIRST FALSE


    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
