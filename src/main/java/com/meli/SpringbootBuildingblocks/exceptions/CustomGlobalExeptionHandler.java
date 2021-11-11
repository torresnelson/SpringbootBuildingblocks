package com.meli.SpringbootBuildingblocks.exceptions;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExeptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
        "From MethodArgumentNotValid Exception in Global handler exception", ex.getMessage());
    return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
        "From HttpRequestMethodNotSupported Exception in Global handler exception",
        ex.getMessage());
    return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler
  public final ResponseEntity<Object> handleUsernameNotFound(UsernameNotFoundException e,
      WebRequest request) {
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
        e.getMessage(),
        request.getLocale().getDisplayCountry());
    return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public final ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException e, WebRequest request) {
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
      e.getMessage(),
      request.getLocale().getDisplayCountry());
    return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);}
}
