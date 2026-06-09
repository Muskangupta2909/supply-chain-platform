package com.tracking.tracking_service.service.impl;

import com.tracking.tracking_service.dto.TrackingRequestDTO;
import com.tracking.tracking_service.dto.TrackingResponseDTO;
import com.tracking.tracking_service.entity.TrackingEvent;
import com.tracking.tracking_service.enums.TrackingStatus;
import com.tracking.tracking_service.exception.ResourceNotFoundException;
import com.tracking.tracking_service.mapper.TrackingMapper;
import com.tracking.tracking_service.repository.TrackingRepository;
import com.tracking.tracking_service.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    Tracking Service Implementation
*/
@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingServiceImpl implements TrackingService {

    // Repository injection
    private final TrackingRepository trackingRepository;

    /*
        Create tracking event
     */
    @Override
    public TrackingResponseDTO createTrackingEvent(
            TrackingRequestDTO requestDTO
    ) {

        // Log tracking creation request
        log.info("Creating tracking event for shipment ID: {}",
                requestDTO.shipmentId());

        // Convert DTO to entity
        TrackingEvent trackingEvent =
                TrackingMapper.toEntity(requestDTO);

        // Save tracking event
        TrackingEvent savedEvent =
                trackingRepository.save(trackingEvent);

        // Log successful tracking creation
        log.info("Tracking event created successfully with ID: {}",
                savedEvent.getId());

        // Convert entity to response DTO
        return TrackingMapper.toResponseDTO(savedEvent);
    }

    /*
        Get tracking history by shipment ID
     */
    @Override
    public List<TrackingResponseDTO> getTrackingByShipmentId(
            Long shipmentId
    ) {

        log.info("Fetching tracking history for shipment ID: {}",
                shipmentId);

        // Fetch tracking events by shipment ID
        List<TrackingEvent> trackingEvents =
                trackingRepository.findByShipmentId(shipmentId);

        // Throw exception if no tracking found
        if (trackingEvents.isEmpty()) {

            log.error("Tracking history not found for shipment ID: {}",
                    shipmentId);

            throw new ResourceNotFoundException(
                    "Tracking history not found for shipment ID : "
                            + shipmentId
            );
        }

        // Convert entity list to response DTO list
        return trackingEvents.stream()
                .map(TrackingMapper::toResponseDTO)
                .toList();
    }

    /*
        Get tracking events by status
     */
    @Override
    public List<TrackingResponseDTO> getTrackingByStatus(
            TrackingStatus status
    ) {

        log.info("Fetching tracking events with status: {}",
                status);

        // Fetch tracking events by status
        List<TrackingEvent> trackingEvents =
                trackingRepository.findByStatus(status);

        // Throw exception if no tracking events found
        if (trackingEvents.isEmpty()) {

            log.error("No tracking events found with status: {}",
                    status);

            throw new ResourceNotFoundException(
                    "No tracking events found with status : "
                            + status
            );
        }

        // Convert entity list to response DTO list
        return trackingEvents.stream()
                .map(TrackingMapper::toResponseDTO)
                .toList();
    }

    /*
        Get all tracking events with pagination and sorting
     */
    @Override
    public Page<TrackingResponseDTO> getAllTrackingEvents(
            int page,
            int size,
            String sortBy
    ) {

        // Fetch paginated tracking events
        Page<TrackingEvent> trackingPage =
                trackingRepository.findAll(
                        PageRequest.of(
                                page,
                                size,
                                Sort.by(sortBy)
                        )
                );

        // Convert entity page to DTO page
        return trackingPage.map(
                TrackingMapper::toResponseDTO
        );
    }
}