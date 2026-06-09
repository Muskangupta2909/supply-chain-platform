package com.tracking.tracking_service.common;

import lombok.Builder;

import java.time.LocalDateTime;

/*
    Common API Response Structure
*/
@Builder
public record ApiResponse<T>(

        String status,

        int statusCode,

        String message,

        T data,

        LocalDateTime timestamp
) {
}