package me.javaexample.javademo.exception;

public class CustomException extends RuntimeException {
    private String errorMessage;

    public CustomException(){}

    public CustomException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
