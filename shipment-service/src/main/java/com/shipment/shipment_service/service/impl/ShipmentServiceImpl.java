package com.shipment.shipment_service.service.impl;

import com.shipment.shipment_service.dto.ShipmentRequestDTO;
import com.shipment.shipment_service.dto.ShipmentResponseDTO;
import com.shipment.shipment_service.entity.Shipment;
import com.shipment.shipment_service.enums.ShipmentStatus;
import com.shipment.shipment_service.exception.ResourceNotFoundException;
import com.shipment.shipment_service.mapper.ShipmentMapper;
import com.shipment.shipment_service.repository.ShipmentRepository;
import com.shipment.shipment_service.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    private final ShipmentMapper shipmentMapper;

    // create shipment
    @Override
    public ShipmentResponseDTO createShipment(ShipmentRequestDTO dto) {

        log.info("Creating shipment");

        Shipment shipment =
                shipmentMapper.toEntity(dto);

        Shipment savedShipment =
                shipmentRepository.save(shipment);

        return shipmentMapper
                .toResponseDTO(savedShipment);
    }

    // get all shipments
    @Override
    public Page<ShipmentResponseDTO>
    getAllShipments(
            int page,
            int size,
            String sortBy) {

        Page<Shipment> shipmentPage =
                shipmentRepository.findAll(
                        PageRequest.of(
                                page,
                                size,
                                Sort.by(sortBy)
                        )
                );

        return shipmentPage.map(
                shipmentMapper::toResponseDTO
        );
    }

    // get shipment by id
    @Override
    public ShipmentResponseDTO
    getShipmentById(Long id) {

        Shipment shipment =
                shipmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Shipment not found with id : " + id
                                ));

        return shipmentMapper
                .toResponseDTO(shipment);
    }

    // update shipment
    @Override
    public ShipmentResponseDTO updateShipment(
            Long id,
            ShipmentRequestDTO dto) {

        Shipment shipment =
                shipmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Shipment not found with id : " + id
                                ));

        shipment.setShipmentCode(dto.shipmentCode());
        shipment.setSource(dto.source());
        shipment.setDestination(dto.destination());
        shipment.setStatus(dto.status());
        shipment.setExpectedDelivery(dto.expectedDelivery());


        Shipment updatedShipment =
                shipmentRepository.save(shipment);

        return shipmentMapper
                .toResponseDTO(updatedShipment);
    }

    // delete shipment
    @Override
    public void deleteShipment(Long id) {

        Shipment shipment =
                shipmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Shipment not found with id : " + id
                                ));

        shipmentRepository.delete(shipment);
    }

    // get shipments by status
    @Override
    public List<ShipmentResponseDTO>
    getShipmentsByStatus(
            ShipmentStatus status) {

        List<Shipment> shipments =
                shipmentRepository.findByStatus(status);

        return shipments.stream()
                .map(shipmentMapper::toResponseDTO)
                .toList();
    }
}