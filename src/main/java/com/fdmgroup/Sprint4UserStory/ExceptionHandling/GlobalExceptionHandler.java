package com.fdmgroup.Sprint4UserStory.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // GlobalExceptionHandler, centralizes exception handling and returns clean JSON responses

    //Customer not found exception handler
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCustomerNotFound(CustomerNotFoundException ex) {

        Map<String, Object> error = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {

        //get all field errors and join them in a message
        List<String> errors = new ArrayList<>();
        for(FieldError fieldError: ex.getBindingResult().getFieldErrors()){
            errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        String message = String.join(", ", errors);

        Map<String, Object> error = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 400,
                "error", "Bad Request",
                "message", message
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // This is thrown when the geocoder.ca API fails or returns invalid data while resolving a postal code.
    @ExceptionHandler(GeoCoderException.class)
    public ResponseEntity<Map<String, Object>> handleGeocoderException(GeoCoderException ex) {

        Map<String, Object> error = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 502,
                "error", "Bad Gateway",
                "message", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }
}