package com.shipment.shipment_service.service;

import com.shipment.shipment_service.dto.ShipmentRequestDTO;
import com.shipment.shipment_service.dto.ShipmentResponseDTO;
import com.shipment.shipment_service.enums.ShipmentStatus;
import org.springframework.data.domain.Page;

import java.util.List;

// service interface for shipment operations

public interface ShipmentService {

    // create shipment
    ShipmentResponseDTO createShipment(ShipmentRequestDTO dto);

    // get all shipments with pagination & sorting
    Page<ShipmentResponseDTO> getAllShipments(
            int page,
            int size,
            String sortBy
    );

    // get shipment by id
    ShipmentResponseDTO getShipmentById(Long id);

    // update shipment
    ShipmentResponseDTO updateShipment(
            Long id,
            ShipmentRequestDTO dto);

    // delete shipment
    void deleteShipment(Long id);

    // get shipments by status
    List<ShipmentResponseDTO> getShipmentsByStatus(ShipmentStatus status);
}