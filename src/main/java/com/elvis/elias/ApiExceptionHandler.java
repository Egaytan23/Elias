package com.elvis.elias;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // makes this class avaliable to handle expection from our controller
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class) // selects the expection type this method handles
    @ResponseStatus(HttpStatus.BAD_REQUEST) // sets the response to 400
    public Map<String, String> handleIllegalArgument(
            IllegalArgumentException exception) {

        return Map.of("error", exception.getMessage()); // retrieves the message we supplied when throwing the exception
    }
}

//HttpStatus is a enum which defines a set of named constants