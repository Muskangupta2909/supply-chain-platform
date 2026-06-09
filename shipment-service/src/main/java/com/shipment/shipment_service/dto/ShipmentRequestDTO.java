package com.shipment.shipment_service.dto;

import com.shipment.shipment_service.enums.ShipmentStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

// request dto for creating shipment

public record ShipmentRequestDTO(

        @NotBlank(message = "Shipment code is required")
        // unique shipment code
        String shipmentCode,

        @NotBlank(message = "Source is required")
        // shipment source location
        String source,

        @NotBlank(message = "Destination is required")
        // shipment destination location
        String destination,

        @NotNull(message = "Status is required")
        // shipment current status
        ShipmentStatus status,

        @Future(message = "Expected delivery must be future date")
        // expected delivery date/time
        LocalDateTime expectedDelivery

) {
}