package com.quizzetta.Validator;

import com.quizzetta.DAOs.QuizDAO;
import com.quizzetta.Errors.AppError;
import com.quizzetta.Errors.UserNotFoundError;
import com.quizzetta.Errors.ValidationError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizValidator implements Validator {

//    private final String id;
    private final String questionCount;
    private final List<ValidationError> errors;
//    private QuizDAO quizDAO;

    public QuizValidator(String questionCount) {
        this.questionCount = questionCount;
        this.errors = new ArrayList<>();
    }


    @Override
    public boolean validate() {
        try {
            int count = Integer.parseInt(questionCount);
            if (count < 1) {
                errors.add(new ValidationError("The number of questions has to be greater than 1"));
                return false;
            }
            if (count > 99) {
                errors.add(new ValidationError("The number of questions has to be less than 100"));
            }

            return true;
        } catch (NumberFormatException e) {
            errors.add(new ValidationError("Please enter an integer type for the question count"));
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
