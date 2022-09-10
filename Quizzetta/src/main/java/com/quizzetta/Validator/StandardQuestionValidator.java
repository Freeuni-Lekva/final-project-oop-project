package com.quizzetta.Validator;

import com.quizzetta.Errors.ValidationError;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandardQuestionValidator implements Validator {


    private String question;
    private String answer;
    private List<ValidationError> errors;

    public StandardQuestionValidator(String questionInput, String answerInput) {
        this.question = questionInput;
        this.answer = answerInput;
        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() {
        if (question == null || question.isEmpty()) {
            errors.add(new ValidationError("The question field cannot be empty!"));
            return false;
        }
        if (answer == null || answer.isEmpty()) {
            errors.add(new ValidationError("The answer field cannot be empty!"));
            return false;
        }

        return true;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
