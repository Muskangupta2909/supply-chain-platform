package com.tracking.tracking_service.entity;

import com.tracking.tracking_service.enums.TrackingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
    Tracking Event Entity
*/
@Entity
@Table(name = "tracking_events")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shipmentId;

    private String location;

    @Enumerated(EnumType.STRING)
    private TrackingStatus status;

    private String remarks;

    private LocalDateTime eventTime;

    // Record creation timestamp
    @CreatedDate
    private LocalDateTime createdAt;

    // Record last update timestamp
    @LastModifiedDate
    private LocalDateTime updatedAt;
}