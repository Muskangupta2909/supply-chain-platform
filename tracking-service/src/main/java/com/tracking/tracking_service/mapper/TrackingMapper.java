package com.tracking.tracking_service.mapper;

import com.tracking.tracking_service.dto.TrackingRequestDTO;
import com.tracking.tracking_service.dto.TrackingResponseDTO;
import com.tracking.tracking_service.entity.TrackingEvent;

/*
    Tracking Mapper
*/
public class TrackingMapper {

    /*
        Convert Request DTO to Entity
     */
    public static TrackingEvent toEntity(
            TrackingRequestDTO dto
    ) {

        return TrackingEvent.builder()
                .shipmentId(dto.shipmentId())
                .location(dto.location())
                .status(dto.status())
                .remarks(dto.remarks())
                .eventTime(dto.eventTime())
                .build();
    }

    /*
        Convert Entity to Response DTO
     */
    public static TrackingResponseDTO toResponseDTO(
            TrackingEvent trackingEvent
    ) {

        return TrackingResponseDTO.builder()
                .id(trackingEvent.getId())
                .shipmentId(trackingEvent.getShipmentId())
                .location(trackingEvent.getLocation())
                .status(trackingEvent.getStatus())
                .remarks(trackingEvent.getRemarks())
                .eventTime(trackingEvent.getEventTime())
                .createdAt(trackingEvent.getCreatedAt())
                .updatedAt(trackingEvent.getUpdatedAt())
                .build();
    }
}