package com.example.hr_management.exception;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  // Handle Validation Errors
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    // Get list of all validation errors from the exception object
    List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();

    // Create a StringBuilder to store all error messages
    StringBuilder sb = new StringBuilder();

    // Loop through all the errors and append the error messages to the
    // StringBuilder
    for (ObjectError error : validationErrors) {
      sb.append(error.getDefaultMessage() + " ");
    }

    // Create custom error response
    ErrorResponse errorResonse = new ErrorResponse(sb.toString());

    // Return
    return new ResponseEntity<Object>(errorResonse, HttpStatus.BAD_REQUEST);

  }

  // Handle All Other Exceptions
  // General exception handler - handle any uncaught exception
  @ExceptionHandler({ DepartmentNotFoundException.class, EmployeeNotFoundException.class,
      DependentNotFoundException.class, JobNotFoundException.class })
  public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

  }

  // Handle Unsucessful Deletion
  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Object> EmptyResultDataAccessException(EmptyResultDataAccessException ex) {
    ErrorResponse errorResponse = new ErrorResponse("Cannot delete item that doesn't exist.");
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

}
