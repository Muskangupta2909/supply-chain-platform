package com.shipment.shipment_service.controller;

import com.shipment.shipment_service.common.ApiResponse;
import com.shipment.shipment_service.dto.ShipmentRequestDTO;
import com.shipment.shipment_service.dto.ShipmentResponseDTO;
import com.shipment.shipment_service.enums.ShipmentStatus;
import com.shipment.shipment_service.service.ShipmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor

public class ShipmentController {

    private final ShipmentService shipmentService;

    // create shipment
    @PostMapping
    public ResponseEntity<
            ApiResponse<ShipmentResponseDTO>>
    createShipment(

            @Valid
            @RequestBody
            ShipmentRequestDTO dto) {

        ShipmentResponseDTO responseDTO =
                shipmentService.createShipment(dto);

        return ResponseEntity.status(201).body(

                ApiResponse.<ShipmentResponseDTO>
                                builder()

                        .status("SUCCESS")

                        .statusCode(201)

                        .message(
                                "Shipment created successfully"
                        )

                        .data(responseDTO)

                        .timestamp(
                                LocalDateTime.now()
                        )

                        .build()
        );
    }

     /*
       GET ALL SHIPMENTS API
    */

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ShipmentResponseDTO>>>
    getAllShipments(

            @RequestParam(defaultValue = "0")
            @Min(0)
            int page,

            @RequestParam(defaultValue = "5")
            @Min(1)
            @Max(10)
            int size,

            @RequestParam(defaultValue = "createdAt")
            String sortBy
    ) {

        Page<ShipmentResponseDTO> shipments =
                shipmentService.getAllShipments(
                        page,
                        size,
                        sortBy
                );

        return ResponseEntity.ok(

                ApiResponse.<Page<ShipmentResponseDTO>>
                                builder()

                        .status("SUCCESS")
                        .statusCode(200)
                        .message("Shipments fetched successfully")
                        .data(shipments)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // get shipment by id
    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<ShipmentResponseDTO>> getShipmentById(@PathVariable Long id) {

        ShipmentResponseDTO shipment = shipmentService.getShipmentById(id);

        return ResponseEntity.ok(

                ApiResponse.<ShipmentResponseDTO>builder()

                        .status("SUCCESS")
                        .statusCode(200)
                        .message("Shipment fetched successfully")
                        .data(shipment)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // update shipment
    @PutMapping("/{id}")
    public ResponseEntity<
            ApiResponse<ShipmentResponseDTO>>
    updateShipment(
            @PathVariable Long id,
            @Valid
            @RequestBody
            ShipmentRequestDTO dto
    ) {

        ShipmentResponseDTO updatedShipment =
                shipmentService.updateShipment(id, dto);

        return ResponseEntity.ok(

                ApiResponse.<ShipmentResponseDTO>builder()

                        .status("SUCCESS")
                        .statusCode(200)
                        .message("Shipment updated successfully")
                        .data(updatedShipment)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // delete shipment
    @DeleteMapping("/{id}")
    public ResponseEntity<
            ApiResponse<String>> deleteShipment(@PathVariable Long id) {

        shipmentService.deleteShipment(id);

        return ResponseEntity.ok(

                ApiResponse.<String>builder()
                        .status("SUCCESS")
                        .statusCode(200)
                        .message("Shipment deleted successfully")
                        .data("Deleted")
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // get shipments by status
    @GetMapping("/status/{status}")
    public ResponseEntity<
            ApiResponse<List<ShipmentResponseDTO>>>
    getShipmentsByStatus(@PathVariable ShipmentStatus status) {

        List<ShipmentResponseDTO> shipments = shipmentService.getShipmentsByStatus(status);

        return ResponseEntity.ok(
                ApiResponse
                        .<List<ShipmentResponseDTO>>builder()
                        .status("SUCCESS")
                        .statusCode(200)
                        .message("Shipments fetched successfully")
                        .data(shipments)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}