package com.shipment.shipment_service.exception;

// custom exception for resource not found

public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(
            String message) {

        super(message);
    }
}