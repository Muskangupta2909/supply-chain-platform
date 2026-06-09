package com.shipment.shipment_service.common;

import lombok.*;

import java.time.LocalDateTime;

// common api response structure

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApiResponse<T> {

    // success or failed
    private String status;

    // http status code
    private int statusCode;

    // response message
    private String message;

    // actual response data
    private T data;

    // response timestamp
    private LocalDateTime timestamp;
}