package com.meli.SpringbootBuildingblocks.exceptions;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExeptionHandler extends ResponseEntityExceptionHandler {

  //MethodArgumentNotValidException
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
        "From MethodArgumentNotValid Exception in Global handler exception", ex.getMessage());
    return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
  }
}
