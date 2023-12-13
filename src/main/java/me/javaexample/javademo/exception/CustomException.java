package me.javaexample.javademo.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private String errorMessage;

    public CustomException(){}

    public CustomException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public CustomException(String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorMessage = errorMessage;
    }

}
