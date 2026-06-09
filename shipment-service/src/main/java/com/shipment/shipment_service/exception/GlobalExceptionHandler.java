package com.shipment.shipment_service.exception;

import com.shipment.shipment_service.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
// global exception handling

public class GlobalExceptionHandler {

    // handle resource not found exception
    @ExceptionHandler(
            ResourceNotFoundException.class)

    public ResponseEntity<ApiResponse<?>>
    handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        ApiResponse<?> response =
                ApiResponse.builder()
                        .status("FAILED")
                        .statusCode(404)
                        .message(ex.getMessage())
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    // handle validation exception
    @ExceptionHandler(
            MethodArgumentNotValidException.class)

    public ResponseEntity<ApiResponse<?>>
    handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));

        ApiResponse<?> response =
                ApiResponse.builder()
                        .status("FAILED")
                        .statusCode(400)
                        .message("Validation failed")
                        .data(errors)
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    // handle all other exceptions
    @ExceptionHandler(Exception.class)

    public ResponseEntity<ApiResponse<?>>
    handleGlobalException(
            Exception ex) {

        ApiResponse<?> response =
                ApiResponse.builder()
                        .status("FAILED")
                        .statusCode(500)
                        .message(ex.getMessage())
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}