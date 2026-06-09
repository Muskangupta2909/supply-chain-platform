package com.shipment.shipment_service.repository;

import com.shipment.shipment_service.entity.Shipment;
import com.shipment.shipment_service.enums.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// repository layer for database operations

public interface ShipmentRepository
        extends JpaRepository<Shipment, Long> {

    // find shipments by status
    List<Shipment> findByStatus(
            ShipmentStatus status);
}