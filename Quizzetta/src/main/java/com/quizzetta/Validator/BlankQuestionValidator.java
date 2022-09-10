package com.quizzetta.Validator;

import com.quizzetta.Errors.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class BlankQuestionValidator implements Validator {
    private final String questionPartOne;
    private final String questionPartTwo;
    private String answer;
    List<ValidationError> errors;



    public BlankQuestionValidator(String questionPartOne, String answer, String questionPartTwo) {
        this.answer = answer;
        this.questionPartOne = questionPartOne;
        this.questionPartTwo = questionPartTwo;
        this.errors = new ArrayList<>();
}


    @Override
    public boolean validate() {
        if (answer == null || answer.isEmpty()) {
            errors.add(new ValidationError("The answer field cannot be empty!"));
            return false;
        }
        if ((questionPartOne == null || questionPartOne.isEmpty()) && (questionPartTwo == null || questionPartTwo.isEmpty())) {
            errors.add(new ValidationError("You have to fill at least one part of the question!"));
            return false;
        }

        return true;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
