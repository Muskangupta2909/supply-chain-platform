package com.tracking.tracking_service.service;

import com.tracking.tracking_service.dto.TrackingRequestDTO;
import com.tracking.tracking_service.dto.TrackingResponseDTO;
import com.tracking.tracking_service.enums.TrackingStatus;
import org.springframework.data.domain.Page;

import java.util.List;

/*
    Tracking Service Interface
*/
public interface TrackingService {

    /*
        Create tracking event
     */
    TrackingResponseDTO createTrackingEvent(
            TrackingRequestDTO requestDTO
    );

    /*
        Get tracking history by shipment ID
     */
    List<TrackingResponseDTO> getTrackingByShipmentId(
            Long shipmentId
    );

    /*
        Get tracking events by status
     */
    List<TrackingResponseDTO> getTrackingByStatus(
            TrackingStatus status
    );

    /*
        Get all tracking events with pagination and sorting
     */
    Page<TrackingResponseDTO> getAllTrackingEvents(
            int page,
            int size,
            String sortBy
    );
}