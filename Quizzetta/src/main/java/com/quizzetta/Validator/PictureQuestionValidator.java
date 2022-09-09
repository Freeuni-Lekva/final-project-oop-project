package com.quizzetta.Validator;

import com.quizzetta.Errors.ValidationError;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PictureQuestionValidator implements Validator {

    private final String url;
    private final String answer;
    private final String question;
    private ArrayList<ValidationError> errors;

    public PictureQuestionValidator(String url, String questionText, String answer) {
        this.url = url;
        this.answer = answer;
        this.question = questionText;
        this.errors = new ArrayList<>();

    }

    @Override
    public boolean validate() {
        System.out.println("URL: " + url);
        if (!isValidURL(this.url)) {
            errors.add(new ValidationError("The URL is incorrect!"));
        }
        else if (answer == null || answer.isEmpty()) {
            errors.add(new ValidationError(("The Answer can't be empty!")));
        }
        else if (question == null || question.isEmpty()) {
            errors.add(new ValidationError(("The Question can't be empty!")));
        }
        else {
            return true;
        }
        return false;
    }

    @Override
    public List<ValidationError> getErrors() {
        return errors;
    }

    boolean isValidURL(String urlInput) {
        try {
            URL url = new URL(urlInput);
            URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            // the URL is not in a valid form
            return false;
        } catch (IOException e) {
            // the connection couldn't be established
            return false;
        }
    }

}
