package com.shipment.shipment_service.mapper;

import com.shipment.shipment_service.dto.ShipmentRequestDTO;
import com.shipment.shipment_service.dto.ShipmentResponseDTO;
import com.shipment.shipment_service.entity.Shipment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component // spring bean

public class ShipmentMapper {

    // convert request dto to entity
    public Shipment toEntity(
            ShipmentRequestDTO dto) {

        return Shipment.builder()
                .shipmentCode(dto.shipmentCode())
                .source(dto.source())
                .destination(dto.destination())
                .status(dto.status())
                .expectedDelivery(dto.expectedDelivery())

                .build();
    }

    // convert entity to response dto
    public ShipmentResponseDTO toResponseDTO(
            Shipment shipment) {

        return new ShipmentResponseDTO(

                shipment.getId(),

                shipment.getShipmentCode(),

                shipment.getSource(),

                shipment.getDestination(),

                shipment.getStatus(),

                shipment.getExpectedDelivery(),

                shipment.getCreatedAt(),

                shipment.getUpdatedAt()
        );
    }
}