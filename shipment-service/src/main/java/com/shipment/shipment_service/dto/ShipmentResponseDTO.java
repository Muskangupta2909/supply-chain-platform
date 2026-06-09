package com.shipment.shipment_service.dto;

import com.shipment.shipment_service.enums.ShipmentStatus;

import java.time.LocalDateTime;

// response dto for sending shipment data

public record ShipmentResponseDTO(

        // shipment id
        Long id,

        // unique shipment code
        String shipmentCode,

        // shipment source
        String source,

        // shipment destination
        String destination,

        // shipment current status
        ShipmentStatus status,

        // expected delivery date
        LocalDateTime expectedDelivery,

        // created timestamp
        LocalDateTime createdAt,

        // updated timestamp
        LocalDateTime updatedAt

) {
}