package me.javaexample.javademo.exception;

import me.javaexample.javademo.api.base.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    ResponseEntity<?> handleCustomException(CustomException ex){
        return new ResponseEntity<>(ApiResult.error(ex.getErrorMessage()), HttpStatus.OK);
    }

}
