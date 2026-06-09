package com.tracking.tracking_service.repository;

import com.tracking.tracking_service.entity.TrackingEvent;
import com.tracking.tracking_service.enums.TrackingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    Tracking Repository
*/
public interface TrackingRepository
        extends JpaRepository<TrackingEvent, Long> {

    /*
        Find tracking events by shipment ID
     */
    List<TrackingEvent> findByShipmentId(
            Long shipmentId
    );

    /*
        Find tracking events by tracking status
     */
    List<TrackingEvent> findByStatus(
            TrackingStatus status
    );
}