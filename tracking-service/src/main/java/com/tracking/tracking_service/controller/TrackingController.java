package com.tracking.tracking_service.controller;

import com.tracking.tracking_service.common.ApiResponse;
import com.tracking.tracking_service.dto.TrackingRequestDTO;
import com.tracking.tracking_service.dto.TrackingResponseDTO;
import com.tracking.tracking_service.enums.TrackingStatus;
import com.tracking.tracking_service.service.TrackingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/*
    Tracking Controller
*/
@RestController
@RequestMapping("/api/v1/tracking")
@RequiredArgsConstructor
public class TrackingController {

    // Service injection
    private final TrackingService trackingService;

    /*
       CREATE TRACKING EVENT API
    */
    @PostMapping
    public ResponseEntity<ApiResponse<TrackingResponseDTO>>
    createTrackingEvent(

            @Valid
            @RequestBody
            TrackingRequestDTO dto
    ) {

        // Call service layer
        TrackingResponseDTO savedTracking = trackingService.createTrackingEvent(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<TrackingResponseDTO>builder()
                                .status("SUCCESS")
                                .statusCode(201)
                                .message("Tracking event created successfully")
                                .data(savedTracking)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    /*
       GET ALL TRACKING EVENTS API
    */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<TrackingResponseDTO>>>
    getAllTrackingEvents(

            @RequestParam(defaultValue = "0")
            @Min(0)
            int page,

            @RequestParam(defaultValue = "5")
            @Min(1)
            @Max(10)
            int size,

            @RequestParam(defaultValue = "eventTime")
            String sortBy
    ) {

        Page<TrackingResponseDTO> trackingEvents =
                trackingService.getAllTrackingEvents(
                        page,
                        size,
                        sortBy
                );

        return ResponseEntity.ok(
                ApiResponse.<Page<TrackingResponseDTO>>builder()
                        .status("SUCCESS")
                        .statusCode(200)
                        .message("Tracking events fetched successfully")
                        .data(trackingEvents)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    /*
       GET TRACKING HISTORY BY SHIPMENT ID API
    */
    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<ApiResponse<List<TrackingResponseDTO>>>
    getTrackingByShipmentId(

            @PathVariable
            Long shipmentId
    ) {

        // Fetch tracking history
        List<TrackingResponseDTO> trackingEvents =
                trackingService.getTrackingByShipmentId(shipmentId);

        return ResponseEntity.ok(
                ApiResponse.<List<TrackingResponseDTO>>builder()
                        .status("SUCCESS")
                        .statusCode(200)
                        .message("Tracking history fetched successfully")
                        .data(trackingEvents)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    /*
       GET TRACKING EVENTS BY STATUS API
    */
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<TrackingResponseDTO>>>
    getTrackingByStatus(

            @PathVariable
            TrackingStatus status
    ) {

        // Fetch tracking events by status
        List<TrackingResponseDTO> trackingEvents =
                trackingService.getTrackingByStatus(status);

        return ResponseEntity.ok(
                ApiResponse.<List<TrackingResponseDTO>>builder()
                        .status("SUCCESS")
                        .statusCode(200)
                        .message("Tracking events fetched successfully")
                        .data(trackingEvents)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}