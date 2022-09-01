package com.quizzetta.Validator;

import com.quizzetta.DAOs.QuizDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.UserNotFoundError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizValidator implements Validator {

//    private final String id;
    private final String questionCount;
    private final List<AppError> errors;
//    private QuizDAO quizDAO;

    public QuizValidator(String questionCount) {
        this.questionCount = questionCount;
        this.errors = new ArrayList<>();
    }


    @Override
    public boolean validate() {
        try {
            Integer.parseInt(questionCount);
            return true;
        } catch (NumberFormatException e) {
            errors.add(new UserNotFoundError("quizId", "Please enter an integer type for the question count"));
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AppError> getErrors() {
        return errors;
    }
}
