package com.tracking.tracking_service.exception;

import com.tracking.tracking_service.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/*
    Global Exception Handler
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
        Handle ResourceNotFoundException
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>>
    handleResourceNotFoundException(
            ResourceNotFoundException ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .status("FAILED")
                        .statusCode(404)
                        .message(ex.getMessage())
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    /*
        Handle Validation Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>>
    handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        // Extract first validation error
        String errorMessage = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .status("FAILED")
                        .statusCode(400)
                        .message(errorMessage)
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.badRequest()
                .body(response);
    }

    /*
        Handle unexpected/unhandled exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>>
    handleGlobalException(
            Exception ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .status("FAILED")
                        .statusCode(500)
                        .message(ex.getMessage())
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(response);
    }
}