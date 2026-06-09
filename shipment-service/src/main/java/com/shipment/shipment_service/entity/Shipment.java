package com.shipment.shipment_service.entity;

import com.shipment.shipment_service.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // marks this class as database entity

@Table(name = "shipments") // table name

@EntityListeners(AuditingEntityListener.class)
// enables jpa auditing

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // primary key
    private Long id;

    @Column(unique = true, nullable = false)
    // unique shipment code
    private String shipmentCode;

    @Column(nullable = false)
    // shipment source location
    private String source;

    @Column(nullable = false)
    // shipment destination
    private String destination;

    @Enumerated(EnumType.STRING)
    // enum value store as string
    private ShipmentStatus status;

    // expected delivery date/time
    private LocalDateTime expectedDelivery;

    @CreatedDate
    // auto created timestamp
    private LocalDateTime createdAt;

    @LastModifiedDate
    // auto updated timestamp
    private LocalDateTime updatedAt;
}