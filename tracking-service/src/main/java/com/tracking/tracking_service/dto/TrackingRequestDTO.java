package com.tracking.tracking_service.dto;

import com.tracking.tracking_service.enums.TrackingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

/*
    Tracking Request DTO
*/
@Builder
public record TrackingRequestDTO(

        @NotNull(message = "Shipment ID is required")
        Long shipmentId,

        @NotBlank(message = "Location is required")
        String location,

        @NotNull(message = "Tracking status is required")
        TrackingStatus status,

        String remarks,

        @NotNull(message = "Event time is required")
        LocalDateTime eventTime
) {
}