package com.tracking.tracking_service.exception;

/*
    Custom Exception for Resource Not Found
*/
public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(
            String message
    ) {
        super(message);
    }
}