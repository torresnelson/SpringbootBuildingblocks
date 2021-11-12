package com.meli.SpringbootBuildingblocks.exceptions;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class CustomRestControllerAdviceExceptionHandler {

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> UsernameNotFound(UsernameNotFoundException e) {
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
        "From, @RestControllerAdvice  NOT FOUND",
        e.getMessage());
    return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
  }
}
