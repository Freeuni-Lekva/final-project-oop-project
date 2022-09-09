package com.quizzetta.Errors;

public class NotLoggedInException extends Throwable{
    private final String message;
    public NotLoggedInException(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
