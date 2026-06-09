package com.tracking.tracking_service.dto;

import com.tracking.tracking_service.enums.TrackingStatus;
import lombok.Builder;

import java.time.LocalDateTime;

/*
    Tracking Response DTO
*/
@Builder
public record TrackingResponseDTO(

        Long id,

        Long shipmentId,

        String location,

        TrackingStatus status,

        String remarks,

        LocalDateTime eventTime,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}