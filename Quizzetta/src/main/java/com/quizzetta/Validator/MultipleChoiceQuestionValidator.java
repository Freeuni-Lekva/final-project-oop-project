package com.quizzetta.Validator;

import com.quizzetta.Errors.ValidationError;
import com.quizzetta.Model.Answer;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestionValidator implements Validator {
    private final List<Answer> answers;
    private final String question;
    private ArrayList<ValidationError> errors;


    public MultipleChoiceQuestionValidator(String question, List<Answer> answers) {
        this.answers = answers;
        this.errors = new ArrayList<>();
        this.question = question;
    }

    @Override
    public boolean validate() {

        if (question == null || question.isEmpty()) {
            errors.add(new ValidationError("The question field cannot be empty!"));
            return false;
        }

        System.out.println("erti");

        // the correct answer is inputted at 4th, not the most elegant solution, I know.
        if (answers.get(3).getText() == null || answers.get(3).getText().isEmpty()) {
            errors.add(new ValidationError("The correct answer field cannot be empty!"));
            return false;
        }

        System.out.println("orii");

        for (Answer answer : answers) {
            System.out.println(answer.getText());
        }


        int nonNullCnt = 0;
        for (Answer answer : answers) {
            if (answer.getText() != null & !answer.getText().isEmpty()) {
                nonNullCnt++;
            }
        }
        System.out.println("sami");

        System.out.println("VALID ANSWERS: " + nonNullCnt);

        if (nonNullCnt < 2) {
            errors.add(new ValidationError("You must enter at least two answers!"));
        } else {
            return true;
        }
        return false;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }
}
