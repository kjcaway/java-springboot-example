package me.javaexample.javademo.exception;

import me.javaexample.javademo.api.base.ApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LogManager.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    ResponseEntity<?> handleCustomException(CustomException ex){
        logger.error(ex.getErrorMessage(), ex);

        return new ResponseEntity<>(ApiResult.error(ex.getErrorMessage()), HttpStatus.OK);
    }

}
