package me.javaexample.javademo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorMessage;

    public CustomException() {
    }

    public CustomException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public CustomException(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public CustomException(HttpStatus httpStatus, String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
