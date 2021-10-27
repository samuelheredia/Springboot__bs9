package com.bosonit.springboot.db2.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//@ControllerAdvice
// @RestController
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<CustomError> handleNotFoundException(NotFoundException ex) {
        CustomError customError = new CustomError(new Date(), HttpStatus.NOT_FOUND.value() ,ex.getMessage());
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<CustomError> handleUnprocesableException(UnprocesableException ex){
        CustomError customError = new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value() ,ex.getMessage());
        return new ResponseEntity<>(customError, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}