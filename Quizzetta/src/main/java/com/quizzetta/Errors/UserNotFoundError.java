package com.quizzetta.Errors;

public class UserNotFoundError implements AppError {

    private final String errorMessage;
    private final String inputFieldName;

    public UserNotFoundError(String inputFieldName, String errorMessage) {
        this.inputFieldName = inputFieldName;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
