package com.quizzetta.Errors;

public class EmptyInputError implements AppError {

    private final String errorMessage;
    private final String inputFieldName;
    private final String errorName;

    public EmptyInputError(String inputFieldName, String errorMessage) {
        this.errorName = "EmptyInputError";
        this.inputFieldName = inputFieldName;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
