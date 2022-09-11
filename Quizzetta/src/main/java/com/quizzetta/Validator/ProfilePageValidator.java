package com.quizzetta.Validator;

import com.quizzetta.Errors.ValidationError;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ProfilePageValidator implements Validator {

    private final List<ValidationError> errors;
    private String firstName;
    private String lastName;
    private String email;
    private String url;

    public ProfilePageValidator(String firstName, String lastName, String email, String url) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.url = url;
        this.errors = new ArrayList<>();
    }

    @Override
    public boolean validate() {
        if (firstName == null || firstName.isEmpty()) {
            errors.add(new ValidationError("The first name field can't be empty!"));
        } else if (lastName == null || lastName.isEmpty()) {
            errors.add(new ValidationError("The Last name field can't be empty!"));
        } else if (email == null || email.isEmpty()) {
            errors.add(new ValidationError("The email field can't be empty!"));
        } else if (!email.contains("@")) {
            errors.add(new ValidationError("Please put in correct email!"));
        } else if (url == null || url.isEmpty()) {
            return true; // user can skip url input
        } else if (!isValidURL(url)) {
            errors.add(new ValidationError("The url is invalid!"));
        } else if (!(url.endsWith("jpeg") || url.endsWith("jpg") || url.endsWith("png") || url.endsWith("gif"))) {
            errors.add(new ValidationError("The given url does not represent an image."));
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
